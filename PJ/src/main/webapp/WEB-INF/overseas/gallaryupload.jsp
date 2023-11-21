<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Upload</title>
	<style>
		#form {
			text-align: center;
		}
		
		img {
			width: 300px;
			height: 300px;
		}
	</style>
</head>
<body>
	<div id="form">
		<h2>写真アップロード</h2>
		<img id="preview">
		<form id="picform" action="/gallary/uploadproc" method="POST" enctype="multipart/form-data" onsubmit="return validate()">
			<input type="file" id="pic" name="pic" accept="image/*" onchange="preview(this)" value=""><br>
			<input type="button" onclick="picSubmit()" value="アップロード">
			<input type="button" onclick="window.close()" value="キャンセル">
		</form>
	</div>
</body>
<script>
	function preview(input) {
		var reg = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
		var filename = input.value;
		if(filename.match(reg)) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.getElementById('preview').src = e.target.result;
				};
				reader.readAsDataURL(input.files[0]);
			} else {
				document.getElementById('preview').src = "";
			}
		}else {
			alert('イメージファイルのみアップロード可能です。');
			document.getElementById('preview').src = "";
			input.value = "";
		}
	}
	
	function validate() {
		var filename = document.getElementById("pic").value;
		if(filename=="") {
			alert('ファイルが選択されていません。');
			return false;
		}else {
			return true;
		}
	}
	
	function picSubmit() {
		validate();
		var formData = new FormData();
	    formData.append('pic', document.getElementById('picform')[0].files[0]);
	    fetch('/gallary/uploadproc', {
	        method: 'POST',
	        body: formData
	    })
	    .then(resp => {return resp.text();})
	    .then(text => {
	    	if(text==1) {
	    		alert('アップロードしました。');
	    		window.close();
	    	}else {
	    		alert('エラーが発生しました。');
	    		location.reload();
	    	}
	    });
	}
</script>
</html>