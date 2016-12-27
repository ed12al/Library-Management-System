<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label='Page navigation'>
	<ul class='pagination'>
		<li ${pageNo < 2 ? "class='disabled'": ""}>
			<a href='#' aria-label='Previous' ${pageNo > 1 ? "onclick='viewPublishers(".concat(pageNo-1).concat(")'") : ""}>
				<span aria-hidden='true'>&laquo;</span></a>
		</li>
		<c:forEach var="i" begin="1" end="${pages}">
			<li ${pageNo == i ? "class='disabled'" : ""}>
				<a href='#' ${pageNo == i ? "" : "onclick='viewPublishers(".concat(i).concat(")'")}>${i}</a></li>	
		</c:forEach>
		<li ${pageNo >= pages ? "class='disabled" : ""}>
			<a href='#' aria-label='Next' ${pageNo < pages ? "onclick='viewPublishers(".concat(pageNo+1).concat(")'") : ""}>
				<span aria-hidden='true'>&raquo;</span></a>
		</li>
	</ul>
</nav>

<c:set var="step" value="${(pageNo-1)*10}"></c:set>

<table class='table'>
	<tr><th>#</th><th>Publisher Name</th><th>View Detail</th><th>Edit Publisher</th><th>Delete Publisher</th></tr>
	<c:forEach items="${publishers}" var="p" varStatus="status">
		<tr>
			<td>${status.count+step}</td>
			<td>${p.publisherName}</td>
			<td><button class='btn btn-info' data-toggle='modal' data-target='#viewPublisherModal' onclick='viewPublisher(${p.publisherId})'>View</button></td>
			<td><button class='btn btn-success' data-toggle='modal' data-target='#editPublisherModal' onclick='editPublisher(${p.publisherId})'>Edit</button></td>
			<td><button class='btn btn-danger' data-toggle='modal' data-target='#deletePublisherModal' onclick='deletePublisher(${p.publisherId})'>Delete</button></td>
		</tr>
	</c:forEach>
</table>