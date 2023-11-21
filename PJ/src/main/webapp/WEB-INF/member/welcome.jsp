<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="welcome">
	<h1>会員登録が完了されました。</h1>
	<p>ようこそ、 ${id}さま。</p>
	<button type="button" class="btn btn-success" onclick="location.href='/'">ホームへ</button>
	<button type="button" class="btn btn-primary" onclick="location.href='/member/view?id=${id}'">会員情報ページへ</button>
</div>