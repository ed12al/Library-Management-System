<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label='Page navigation'>
	<ul class='pagination'>
		<li ${pageNo < 2 ? "class='disabled'": ""}>
			<a href='#' aria-label='Previous' ${pageNo > 1 ? "onclick='viewAuthors(".concat(pageNo-1).concat(")'") : ""}>
				<span aria-hidden='true'>&laquo;</span></a>
		</li>
		<c:forEach var="i" begin="1" end="${pages}">
			<li ${pageNo == i ? "class='disabled'" : ""}>
				<a href='#' ${pageNo == i ? "" : "onclick='viewAuthors(".concat(i).concat(")'")}>${i}</a></li>	
		</c:forEach>
		<li ${pageNo >= pages ? "class='disabled" : ""}>
			<a href='#' aria-label='Next' ${pageNo < pages ? "onclick='viewAuthors(".concat(pageNo+1).concat(")'") : ""}>
				<span aria-hidden='true'>&raquo;</span></a>
		</li>
	</ul>
</nav>

<c:set var="step" value="${(pageNo-1)*10}"></c:set>

<table class='table'>
	<tr><th>#</th><th>Author Name</th><th>View Detail</th><th>Edit Author</th><th>Delete Author</th></tr>
	<c:forEach items="${authors}" var="a" varStatus="status">
		<tr>
			<td>${status.count+step}</td>
			<td>${a.authorName}</td>
			<td><button class='btn btn-info' data-toggle='modal' data-target='#viewAuthorModal' onclick='viewAuthor(${a.authorId})'>View</button></td>
			<td><button class='btn btn-success' data-toggle='modal' data-target='#editAuthorModal' onclick='editAuthor(${a.authorId})'>Edit</button></td>
			<td><button class='btn btn-danger' data-toggle='modal' data-target='#deleteAuthorModal' onclick='deleteAuthor(${a.authorId})'>Delete</button></td>
		</tr>
	</c:forEach>
</table>