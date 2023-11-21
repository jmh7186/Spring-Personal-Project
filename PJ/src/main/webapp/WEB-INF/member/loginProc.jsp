<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if(${result}==1) {
		location.href="/";
	}else {
		alert('IDまたはパスワードを再確認してください。');
		location.href="/member/login";
	}
</script>