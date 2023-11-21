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
			<h2>投稿</h2>
			<form action="/board/writeProc" method="post" enctype="multipart/form-data">

				<input type="number" id="idx" name="idx" class="form-control" hidden=true>

				<lable for="writeid">投稿者</lable>
				<input type="text" id="writeid" name="writeid" class="form-control" readOnly value="${sessionScope.id}">
					
				<lable for="title">タイトル</lable>
				<input type="text" id="title" name="title" class="form-control">

				<lable for="content">内容</lable>
				<input type="text" id="content" name="content" class="form-control">

				<lable for="file">ファイル添付</lable>
				<input type="file" id="file" name="file" class="form-control">

				<button type="submit" class="btn btn-primary">投稿</button>
				<button type="button" class="btn btn-primary" onclick="location.href='/board/pagelist'">戻る</button>
			</form>
		</div>
	</main>
</body>
</html>