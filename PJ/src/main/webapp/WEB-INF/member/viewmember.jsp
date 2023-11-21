<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="container mt-3">
		<button type="button" class="btn btn-success"
			onclick="location.href='/'">ホームへ</button>
		<h2>会員情報</h2>
		<form action="/member/updateProc" method="POST" onsubmit="return confirmsignup()">
			<div class="mb-3 mt-3">
				<label>ID</label> <input type="text" class="form-control" id="idview" name="idview" disabled value="${member.mid}">
				<input type="text" class="form-control" id="id" name="mid" hidden=true readonly value="${member.mid}">
			</div>
			<div class="mb-3 mt-3">
				<label>パスワード</label>
				<input type="password" class="form-control" id="pwd" name="mpwd" disabled readonly value="${member.mpwd}">
				<input type="button" id="updatePwdBtn" hidden=true class="btn btn-primary" onclick="updatePwd()" value="パスワード変更">
				<input type="password" class="form-control" id="repwd" hidden=true name="repwd" placeholder="パスワード再入力">
				<input type="button" class="btn btn-info" id="pcon" hidden=true onclick="_confirmpwd()" value="パスワード検査">
			</div>
			<div class="mb-3 mt-3">
				<label>携帯電話</label> <input type="text"
					class="form-control" id="hp" name="mhp" disabled value="${member.mhp}">
			</div>
			<div class="mb-3 mt-3">
				<label>住所</label> <input type="text"
					class="form-control" id="adr" name="madr" disabled value="${member.madr}">
			</div>
			<div id="defaultBtns">
				<button type="button" class="btn btn-primary" onclick="mbupdate()">修正</button>
				<button type="button" class="btn btn-danger" onclick="mbdelete()">会員脱退</button>
			</div>
			<div id="updateBtns" hidden=true >
				<button type="submit" class="btn btn-success">修正完了</button>
				<button type="button" class="btn btn-danger" onclick="location.reload()">キャンセル</button>
			</div>
		</form>
	</div>
	<script>
	var pcon = true;
	function mbdelete() {
		var isok = confirm('本当に会員を脱退しますか？');
		if(isok) {
			location.href='/member/deleteProc?id=${member.mid}';
		}
	}
	function mbupdate () {
		document.getElementById("hp").disabled=false;
		document.getElementById("adr").disabled=false;
		document.getElementById("pwd").disabled=false;
		document.getElementById("defaultBtns").hidden=true;
		document.getElementById("updateBtns").hidden=false;
		document.getElementById("updatePwdBtn").hidden=false;
	}
	function updatePwd() {
		document.getElementById("pwd").readOnly=false;
		document.getElementById("updatePwdBtn").hidden=true;
		document.getElementById("repwd").hidden=false;
		document.getElementById("pcon").hidden=false;
		document.getElementById("pwd").disabled=false;
		document.getElementById("pwd").value="";
		document.getElementById("pwd").placeholder="パスワード";
		pcon = false;
	}
	function _confirmpwd() {
		pcon = confirmpwd();
	}
	</script>