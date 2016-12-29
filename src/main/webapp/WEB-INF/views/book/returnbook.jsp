<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class='table'>
	<tr><th>#</th><th>Book</th><th>Branch</th><th>Date Out</th><th>Date In</th><th>Due Date</th><th>Return</th></tr>
	<c:forEach items="${borrower.loans}" var="l" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${l.book.title}</td>
			<td>${l.branch.branchName}</td>
			<td>${l.dateOut}</td>
			<td>${l.dateIn}</td>
			<td>${l.dueDate}</td>
			<td><button class='btn btn-primary' onclick='returnedBook(${l.loanId})'>Return</button></td>
		</tr>
	</c:forEach>
</table>