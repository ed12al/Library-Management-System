<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label='Page navigation'>
	<ul class='pagination'>
		<li ${pageNo < 2 ? "class='disabled'": ""}>
			<a href='#' aria-label='Previous' ${pageNo > 1 ? "onclick='viewLoans(".concat(pageNo-1).concat(")'") : ""}>
				<span aria-hidden='true'>&laquo;</span></a>
		</li>
		<c:forEach var="i" begin="1" end="${pages}">
			<li ${pageNo == i ? "class='disabled'" : ""}>
				<a href='#' ${pageNo == i ? "" : "onclick='viewLoans(".concat(i).concat(")'")}>${i}</a></li>	
		</c:forEach>
		<li ${pageNo >= pages ? "class='disabled'" : ""}>
			<a href='#' aria-label='Next' ${pageNo < pages ? "onclick='viewLoans(".concat(pageNo+1).concat(")'") : ""}>
				<span aria-hidden='true'>&raquo;</span></a>
		</li>
	</ul>
</nav>

<c:set var="step" value="${(pageNo-1)*10}"></c:set>

<table class='table'>
	<tr><th>#</th><th>Book</th><th>Borrower</th><th>Branch</th><th>Date Out</th><th>Date In</th><th>Due Date</th><th>Date Difference To Update</th><th>Update</th><th>Delete</th></tr>
	<c:forEach items="${loans}" var="l" varStatus="status">
		<tr>
			<td>${status.count+step}</td>
			<td>${l.book.title}</td>
			<td>${l.borrower.name}</td>
			<td>${l.branch.branchName}</td>
			<td>${l.dateOut}</td>
			<td>${l.dateIn}</td>
			<td>${l.dueDate}</td>
			<td><input type='text' id='editDiff${l.loanId}' placeholder='Date Difference'></td>
			<td><button class='btn btn-success' onclick='updateLoan(${l.loanId})'>Update</button></td>
			<td><button class='btn btn-danger' data-toggle='modal' data-target='#deleteLoanModal' onclick='deleteLoan(${l.loanId})'>Delete</button></td>
		</tr>
	</c:forEach>
</table>