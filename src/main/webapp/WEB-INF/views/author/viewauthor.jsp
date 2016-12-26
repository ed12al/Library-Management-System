<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Author Name:</label>
	<p class='form-control-static'>${author.authorName}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Books:</label>
	<c:forEach items="${author.books}" var="book">
		<p class='form-control-static'>${book.title}</p>
	</c:forEach>
</div>