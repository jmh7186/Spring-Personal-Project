<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if(${result}==-1) {
		alert('IDが重複されます。');
		history.back();
	}else {
		alert('エラーが発生し処理に失敗しました。');
		location.href="/member/login";
	}
</script>