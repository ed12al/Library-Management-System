<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Borrower Name:</label>
	<p class='form-control-static'>${borrower.name}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Borrower Address:</label>
	<p class='form-control-static'>${borrower.address}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Borrower Phone:</label>
	<p class='form-control-static'>${borrower.phone}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Loans:</label>
	<table class='table'>
		<tr><th>#</th><th>Book Title</th><th>Branch Name</th><th>Date Out</th><th>Date In</th><th>Due Date</th></tr>
		<c:forEach items="${borrower.loans}" var="l" varStatus="status">
			<tr>
				<td>${status.count+step}</td>
				<td>${l.book.title}</td>
				<td>${l.branch.branchName}</td>
				<td>${l.dateOut}</td>
				<td>${l.dateIn}</td>
				<td>${l.dueDate}</td>
			</tr>
		</c:forEach>
	</table>
</div>
