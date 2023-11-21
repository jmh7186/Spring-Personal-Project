<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<div id="loginform">
	<form action="/member/loginProc" method="post" onsubmit="return loginValidate()">
		<ul style="list-style: none">
			<li><h1>LOGIN</h1></li>
			<li>ID<br> <input type="text" name="id" id="id"></li>
			<li>パスワード<br> <input type="password" name="pwd"
				id="pwd"></li>
			<li><input type="submit" value="ログイン" id="loginbtn"></li>
			<li><a href="/signup/contract" href="#">会員登録</a><!-- 　|　<a href="/member/forgot" href="#">ID・パスワードを忘れてましたか？</a> --></li>
		</ul>
	</form>
</div>
