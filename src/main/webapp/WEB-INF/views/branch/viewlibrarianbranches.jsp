<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label='Page navigation'>
	<ul class='pagination'>
		<li ${pageNo < 2 ? "class='disabled'": ""}>
			<a href='#' aria-label='Previous' ${pageNo > 1 ? "onclick='viewBranches(".concat(pageNo-1).concat(")'") : ""}>
				<span aria-hidden='true'>&laquo;</span></a>
		</li>
		<c:forEach var="i" begin="1" end="${pages}">
			<li ${pageNo == i ? "class='disabled'" : ""}>
				<a href='#' ${pageNo == i ? "" : "onclick='viewBranches(".concat(i).concat(")'")}>${i}</a></li>	
		</c:forEach>
		<li ${pageNo >= pages ? "class='disabled'" : ""}>
			<a href='#' aria-label='Next' ${pageNo < pages ? "onclick='viewBranches(".concat(pageNo+1).concat(")'") : ""}>
				<span aria-hidden='true'>&raquo;</span></a>
		</li>
	</ul>
</nav>

<c:set var="step" value="${(pageNo-1)*10}"></c:set>

<table class='table'>
	<tr><th>#</th><th>Branch Name</th><th>View Detail</th><th>Edit Branch</th><th>Edit Book Copies</th></tr>
	<c:forEach items="${branches}" var="b" varStatus="status">
		<tr>
			<td>${status.count+step}</td>
			<td>${b.branchName}</td>
			<td><button class='btn btn-info' data-toggle='modal' data-target='#viewBranchModal' onclick='viewBranch(${b.branchId})'>View</button></td>
			<td><button class='btn btn-success' data-toggle='modal' data-target='#editBranchModal' onclick='editBranch(${b.branchId})'>Edit</button></td>
			<td><button class='btn btn-success' data-toggle='modal' data-target='#editBookCopiesModal' onclick='editBookCopies(${b.branchId})'>Edit</button></td>
		</tr>
	</c:forEach>
</table>