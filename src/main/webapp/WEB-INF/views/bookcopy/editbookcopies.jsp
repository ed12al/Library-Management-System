<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class='table'>
	<tr><th>Title</th><th>No of Copies</th></tr>
	<c:forEach items='${branch.bookCopy}' var='bc'>
		<tr>
			<td>${bc.book.title}</td>
			<td><input class='bookCopy form-control' type='text' id='${bc.book.bookId}' value='${bc.noOfCopies}'></td>
		</tr>
	</c:forEach>
	<c:forEach items='${noCopyBooks}' var='b'>
		<tr>
			<td>${b.title}</td>
			<td><input class='bookCopy form-control' type='text' id='${b.bookId}' value='0'></td>
		</tr>
	</c:forEach>
</table>

<input type='hidden' id='editBookCopiesBranchId' value='${branch.branchId}'>