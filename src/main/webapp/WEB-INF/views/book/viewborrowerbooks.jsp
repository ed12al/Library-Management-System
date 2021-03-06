<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label='Page navigation'>
	<ul class='pagination'>
		<li ${pageNo < 2 ? "class='disabled'": ""}>
			<a href='#' aria-label='Previous' ${pageNo > 1 ? "onclick='viewBooks(".concat(pageNo-1).concat(")'") : ""}>
				<span aria-hidden='true'>&laquo;</span></a>
		</li>
		<c:forEach var="i" begin="1" end="${pages}">
			<li ${pageNo == i ? "class='disabled'" : ""}>
				<a href='#' ${pageNo == i ? "" : "onclick='viewBooks(".concat(i).concat(")'")}>${i}</a></li>	
		</c:forEach>
		<li ${pageNo >= pages ? "class='disabled'" : ""}>
			<a href='#' aria-label='Next' ${pageNo < pages ? "onclick='viewBooks(".concat(pageNo+1).concat(")'") : ""}>
				<span aria-hidden='true'>&raquo;</span></a>
		</li>
	</ul>
</nav>

<c:set var="step" value="${(pageNo-1)*10}"></c:set>

<table class='table'>
	<tr><th>#</th><th>Book Name</th><th>View Detail</th><th>Borrow Book</th></tr>
	<c:forEach items="${books}" var="b" varStatus="status">
		<tr>
			<td>${status.count+step}</td>
			<td>${b.title}</td>
			<td><button class='btn btn-info' data-toggle='modal' data-target='#viewBookModal' onclick='viewBook(${b.bookId})'>View</button></td>
			<td><button class='btn btn-info' data-toggle='modal' data-target='#borrowBookModal' onclick='borrowBook(${b.bookId})'>Availability</button></td>
		</tr>
	</c:forEach>
</table>