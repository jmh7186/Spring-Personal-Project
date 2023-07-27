<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<div id="chat">
	<h1>채팅</h1>
	<textarea id="chatbox">환영합니다.&#10;</textarea>
	<br>
	<input type="text" id="chatmsg" placeholder="여기에 입력하세요.">
	<input type="button" id="chatbtn" onclick="sendmsg()" value="전송"><br><br>
	<input type="button" id="clnbtn" onclick="clean()" value="채팅창 비우기">
</div>

<script>
	var ws = new WebSocket("ws://localhost:8080/chat/${sessionScope.id}");
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
			document.getElementById("chatbox").innerHTML += json.id + " : " + json.msg + "&#10;";
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