<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#viewed:hover {
	background-color: rgb(230, 230, 230);
}
</style>
<div class="container mt-3">
	<h2>一般掲示板</h2>
	<!-- <div class="input-group mt-3 mb-3">
		<button name="kind" id="kind" type="button"
			class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			サーチ</button>
		<div class="dropdown-menu">
			<a class="dropdown-item" href="#" onclick="selkind('title')">タイトル</a>
			<a class="dropdown-item" href="#" onclick="selkind('writeid')">投稿者</a>
			<a class="dropdown-item" href="#" onclick="selkind('writeday')">投稿日</a>
		</div>
		<input type="text" class="form-control" id="search" name="search">
		<input id="searchbtn" type="button" class="btn btn-info" value="検索"
			onclick="selfind()">
	</div> -->
	<table class="table">
		<thead>
			<tr>
				<th>文番号</th>
				<th>タイトル</th>
				<th>投稿者</th>
				<th>投稿日</th>
				<th>視聴数</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="b" items="${pagelist.list}">
				<c:choose>
					<c:when test="${b.isdel!=1}">
						<tr
							onclick="location.href='/board/findone?idx=${b.idx}&page=${param.page}'"
							id="viewed">
							<td>${b.idx}</td>
							<td><c:forEach var="i" begin="1" end="${b.tab}" step="1">
									<img src="/img/reply_icon.gif">
								</c:forEach> ${b.title} <c:if test="${not empty b.filename}">
									<img src="/img/file.png" style="width: 16px; height: 16px;">
								</c:if></td>
							<td>${b.writeid}</td>
							<td>${b.writeday}</td>
							<td>${b.viewcount}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr style="color: gray;">
							<td colspan="5">この文は削除されました。</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</tbody>
	</table>

	<ul class="pagination">
		<c:if test="${pagelist.currentPage>=5}">
			<li class="page-item"><a class="page-link"
				href="/board/pagelist?page=${pagelist.startPage-1}">Previous</a></li>
		</c:if>
		<c:forEach var="i" begin="${pagelist.startPage}"
			end="${pagelist.endPage}" step="1">
			<c:choose>
				<c:when test="${pagelist.currentPage==i}">
					<li class="page-item active"><a class="page-link"
						href="/board/pagelist?page=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/board/pagelist?page=${i}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pagelist.endPage!=pagelist.totalPage}">
			<li class="page-item"><a class="page-link"
				href="/board/pagelist?page=${pagelist.endPage+1}">Next</a></li>
		</c:if>
	</ul>


	<button type="button" class="btn btn-success"
		onclick="location.href='/board/writeform'">投稿</button>
</div>
<script>
	function selkind(kind) {
		if (kind == 'title') {
			document.getElementById("kind").innerText = 'タイトル';
		} else if (kind == 'writeid') {
			document.getElementById("kind").innerText = '投稿者';
		} else if (kind == 'writeday') {
			document.getElementById("kind").innerText = '投稿日';
		}
	}
	function selfind() {
		var kind = document.getElementById("kind").innerText;
		var keyword = document.getElementById("search").value;
		if (kind == 'タイトル') {
			location.href = "/findList?kind=title&keyword=" + keyword;
		} else if (kind == 'タイトル') {
			location.href = "/findList?kind=writeid&keyword=" + keyword;
		} else if (kind == '投稿日') {
			location.href = "/findList?kind=writeday&keyword=" + keyword;
		}
	}
</script>