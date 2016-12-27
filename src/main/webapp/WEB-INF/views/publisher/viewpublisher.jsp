<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Publisher Name:</label>
	<p class='form-control-static'>${publisher.publisherName}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Publisher Address:</label>
	<p class='form-control-static'>${publisher.publisherAddress}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Publisher Phone:</label>
	<p class='form-control-static'>${publisher.publisherPhone}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Books:</label>
	<c:forEach items="${publisher.books}" var="book">
		<p class='form-control-static'>${book.title}</p>
	</c:forEach>
</div>