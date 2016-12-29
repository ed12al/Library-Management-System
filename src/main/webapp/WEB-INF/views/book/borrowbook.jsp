<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class='table'>
	<tr><th>#</th><th>Branch</th><th>No of Copies</th><th>Borrow</th></tr>
	<c:forEach items='${bookCopies}' var='bc' varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${bc.branch.branchName}</td>
			<td>${bc.noOfCopies}</td>
			<td><button class='btn btn-success' onclick='borrowedBook(${bc.branch.branchId}, ${book.bookId})'>Borrow</button></td>
		</tr>
	</c:forEach>
</table>