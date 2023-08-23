<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div id="gallary">
	<button onclick="window.open('/gallary/upload','_blank','width=500px; height=500px; resizable=no;')">사진 게시</button><br>
	<ul>
		<c:forEach var="i" items="${pics}">
			<li><img src="/uploadPics/${i}"></li>
		</c:forEach>
	</ul>
</div>