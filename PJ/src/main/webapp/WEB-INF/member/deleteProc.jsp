<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<script>
	if(${result==1}) {
		alert("会員脱退が完了されました。");
		location.href="/";
	}else {
		alert("エラーが発生し処理に失敗しました。エラーコード：${result}");
		history.go(-2);
	}
</script>