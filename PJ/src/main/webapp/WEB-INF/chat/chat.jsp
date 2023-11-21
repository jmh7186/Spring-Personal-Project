<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<div id="chat">
	<h1>チャット</h1>
	<textarea id="chatbox">&#10;</textarea>
	<br>
	<input type="text" id="chatmsg" placeholder="ここに入力してください。">
	<input type="button" id="chatbtn" onclick="sendmsg()" value="転送"><br><br>
	<input type="button" id="clnbtn" onclick="clean()" value="クリア">
</div>

<script>
	var ws = new WebSocket("ws://joengie.iptime.org/chat/${sessionScope.id}");
	ws.onopen = function(msg) {
		console.log('Open');
	}
	ws.onclose = function(msg) {
		console.log('Close');
	}
	ws.onerror = function(msg) {
		console.log('Error');
	}
	ws.onmessage = function(msg) {
		var json = JSON.parse(msg.data);
		if(json.id!=null) {
			document.getElementById("chatbox").innerHTML += json.id + " [" + json.time + "] " +" : " + json.msg + "&#10;";
		}else {
			document.getElementById("chatbox").innerHTML += json.msg + "&#10;";
		}
		var vScrollDown = $("#chatbox").prop('scrollHeight');
		$("#chatbox").scrollTop(vScrollDown);
	}
	
	function sendmsg() {
		var msg = {
				"id": "${sessionScope.id}",
				"msg": document.getElementById("chatmsg").value
		}
		document.getElementById("chatmsg").value = "";
		ws.send(JSON.stringify(msg));
	}
	
	document.getElementById("chatmsg").addEventListener("keyup",(event) => {
		if(event.keyCode==13) {
			sendmsg();
		}
	});
	
	function clean() {
		document.getElementById("chatbox").innerHTML = "";
	}
</script>