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
		<h2>사진 업로드</h2>
		<img id="preview">
		<form id="picform" action="/gallary/uploadproc" method="POST" enctype="multipart/form-data" onsubmit="return validate()">
			<input type="file" id="pic" name="pic" accept="image/*" onchange="preview(this)" value=""><br>
			<input type="button" onclick="picSubmit()" value="게시">
			<input type="button" onclick="window.close()" value="취소">
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
			alert('이미지 파일만 업로드 가능합니다.');
			document.getElementById('preview').src = "";
			input.value = "";
		}
	}
	
	function validate() {
		var filename = document.getElementById("pic").value;
		if(filename=="") {
			alert('이미지가 선택되지 않았습니다.');
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
	    		alert('정상적으로 게시되었습니다.');
	    		window.close();
	    	}else {
	    		alert('오류가 발생하였습니다.');
	    		location.reload();
	    	}
	    });
	}
</script>
</html>