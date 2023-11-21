<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
	<form action="/signup/signupProc" id="signup" method="post" onsubmit="return confirmsignup()">
		<ul>
			<li><h1>会員登録</h1></li>
			<li style="color:red;"><b><sup>*</sup>必修入力</b></li>
			<li><sup style="color:red"><b>*</b></sup>ID<br><input type="text" id="id" name="id"></li>
			<li><sup style="color:red"><b>*</b></sup>パスワード<br><input type="password" id="pwd" name="pwd"></li>
			<li><sup style="color:red"><b>*</b></sup>パスワード再入力<br><input type="password" id="repwd" name="repwd"></li>
			<li><input type="button" id="pcon" name="pcon" value="パスワード検査" onclick="confirmpwd()"></li>
			<li><sup style="color:red"><b>*</b></sup>携帯電話<br><input type="tel" id="hp" name="hp"></li>
			<li>住所<br><input type="text" id="adr" name="adr"></li>
			<li>
				<input type="submit" value="会員登録">
				<input type="reset" id="reset" name="reset" value="クリア" onclick="location.href='/signup'">
			</li>
		</ul>
	</form>
