<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Branch Name:</label>
	<p class='form-control-static'>${branch.branchName}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Branch Address:</label>
	<p class='form-control-static'>${branch.branchAddress}</p>
</div>

<div class='form-group'>
	<label class='control-label'>BookCopy:</label>
	<table class='table'>
		<tr><th>#</th><th>Book Title</th><th># of Copy</th></tr>
		<c:forEach items="${branch.bookCopy}" var="bc" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${bc.book.title}</td>
				<td>${bc.noOfCopies}</td>
			</tr>
		</c:forEach>
	</table>
</div>