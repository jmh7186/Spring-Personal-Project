<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一般掲示板</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<body>
	<main>
		<div class="container mt-3">
			<h2 id="headertitle">${b.title}</h2>
			<form action="/board/updateProc" method="post"
				enctype="multipart/form-data">

				<lable for="idx">文番号</lable>
				<input type="text" id="idx" name="idx" class="form-control" readonly value="${b.idx}">

				<lable for="writeName">投稿者</lable>
				<input type="text" id="writeid" name="writeid" class="form-control" readonly value="${b.writeid}">

				<lable hidden for="title">제목</lable>
				<input hidden type="text" id="title" name="title" class="form-control" readonly value="${b.title}">

				<lable for="content">内容</lable>
				<input type="text" id="content" name="content" class="form-control" readonly value="${b.content}">

				<lable for="file">ファイル添付</lable>
				<input type=text id="file" name="file" class="form-control" readonly value="${b.filename}">
				
				<br>
				<input type="button" class="btn btn-info" id="reupbtn" value="ファイルアップロード" hidden=true >
				<br>
				
				<br>
				<div id="defaultBtns">
					<input type="button" class="btn btn-primary" value="修正">
					<input type="button" class="btn btn-primary" value="削除">
					<!-- <input type="button" class="btn btn-primary" value="댓글쓰기"> -->
					<input type="button" class="btn btn-primary" value="戻る">
					<input type="button" class="btn btn-info" id="fileopen" value="ファイルオープン" hidden=true>
				</div>
				<div id="updateBtns" hidden=true>
					<input type="submit" class="btn btn-primary" value="修正完了">
					<input type="button" class="btn btn-danger" value="キャンセル">
				</div>
			</form>
		</div>
	</main>
</body>
<script>
	window.onload = function () {
		if(${b.filename!=null}) {
			document.getElementById("fileopen").hidden = false;
		}
	}

	document.getElementById("defaultBtns").addEventListener('click',function (event) {
		if (event.target.value=="修正") {
			document.getElementById("title").readOnly = false;
			document.getElementById("content").readOnly = false;
			document.getElementById("defaultBtns").hidden = true;
			document.getElementById("updateBtns").hidden = false;
			document.getElementById("reupbtn").hidden = false;
		}else if (event.target.value=="削除") {
			var isok = confirm("削除しますか？");
			if(isok) {
				location.href="/board/deleteProc?idx=${b.idx}";
			}
		/* }else if (event.target.value=="댓글쓰기") {
			location.href="/board/replyForm?idx=${b.idx}"; */
		}else if (event.target.value=="戻る") {
			location.href="/board/pagelist?page=${param.page}";
		}else if (event.target.value=="ファイルオープン") {
			window.open("/uploadFiles/${b.filename}", "_blank", "width=500, height=500");
		}
	});
	
	document.getElementById("updateBtns").addEventListener('click',function (event) {
		if(event.target.value=="キャンセル") {
			 location.reload();
		}
	});
	
	document.getElementById("reupbtn").addEventListener('click',function (event) {
		document.getElementById("reupbtn").hidden = true;
		document.getElementById("file").type = "file";
	});
	
	
</script>
</html>