<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h3 id="loading" style="text-align: center; padding-top: 30px;">ただいま読み込んでいます。。。</h3>
<div id="safetyinfo" hidden=true>
	<h4>本ページでは韓国領事館からの各国旅行情報を提供します。</h4>
	<form>
		<input type="text" name="pageNo" value="${pageNo}" hidden=true>
		<input type="search" name="title" size="50" placeholder="題目・国家名で検索（韓国語のみ）" value="${title}">
		<input type="submit" value="検索">
	</form>
	<ul id="safetyinfoul">
		<li><hr></li>
	</ul>
	<c:if test="${pageNo>1}">
		<a class="page" href="?pageNo=${pageNo-1}">前へ</a>
	</c:if>
	<a class="page" href="?pageNo=${pageNo+1}">後へ</a>
	<p/>
</div>
<script>
	var url = `https://apis.data.go.kr/1262000/CountrySafetyService/getCountrySafetyList?serviceKey=WpPySHqOm1qIYdMjmC1LhgGOd6mF9ZjOS6FQwLkv7%2BBRFwSn3wiq2%2F5FWQ1gdGJjOKI%2Bn98PrKsdk42w7uxaQQ%3D%3D&numOfRows=15&pageNo=${pageNo}&title=${title}`;
	fetch(url,{
		type: "GET"
	})
	.then((response) => {return response.text();})
	.then((data) => {
		var item = xmlToJson(parseXML(data)).response.body.items.item;
		document.getElementById("loading").hidden = true;
		document.getElementById("safetyinfo").hidden = false;
		for(i=0;i<item.length;i++) {
			document.querySelector("#safetyinfoul").innerHTML += "<li class='title' onclick='detail("+i+")'><p>" + item[i].wrtDt + "</p>" + item[i].title + "<hr></li>";
			document.querySelector("#safetyinfoul").innerHTML += "<li class='detail' id='D"+i+"' hidden='true'>" + item[i].content + "<hr></li>";
		}
	});
</script>