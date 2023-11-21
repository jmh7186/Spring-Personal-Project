<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	if(${result}==1) {
		alert("会員情報が修正されました。");
		location.href="/member/view?id=${sessionScope.id}";
	}else {
		alert("エラーが発生し処理に失敗しました。エラーコード：${result}");
		history.go(-2);
	}
</script>