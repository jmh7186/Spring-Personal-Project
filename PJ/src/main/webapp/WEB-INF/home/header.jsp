<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar">
	<div class="navbar_logo" onclick="location.href='/'">
		<i class="fab fa-accusoft"></i>
	</div>
	<ul class="navbar_menu">
		<!-- <li onclick="location.href='/phonebook/pagelist'">전화번호부</li> -->
		<li onclick="location.href='/overseas'">各国旅行情報</li>
		<li onclick="location.href='/gallary'">ギャラリー</li>
		<li onclick="location.href='/board/pagelist'">掲示板</li>
		<li onclick="location.href='/chat'">チャット</li>
		<li onclick="location.href='/map'">アクセス</li>
	</ul>
	<ul class="navbar_icon">
		<c:choose>
			<c:when test="${empty sessionScope.id}">
				<li onclick="location.href='/member/login'"><i	class="fas fa-user-plus"></i></li>
			</c:when>
			<c:otherwise>
				<li style="text-align: center">ようこそ。 <a style="color: white" href="/member/view?id=${sessionScope.id}">${sessionScope.id}</a>様<br>
					<a style="color: white" href="/logoutProc">[ログアウト]</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
	<!-- <a class="navbar_toogleBtn" href="#"> <i class="fas fa-bars"></i>
	</a> -->
</nav>
