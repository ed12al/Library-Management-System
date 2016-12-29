<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Title:</label>
	<p class='form-control-static'>${book.title}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Publisher:</label>
	<p class='form-control-static'>${book.publisher.publisherName}</p>
</div>

<div class='form-group'>
	<label class='control-label'>Genres:</label>
	<c:forEach items="${book.genres}" var="genre">
		<p class='form-control-static'>${genre.genreName}</p>
	</c:forEach>
</div>

<div class='form-group'>
	<label class='control-label'>Authors:</label>
	<c:forEach items="${book.authors}" var="author">
		<p class='form-control-static'>${author.authorName}</p>
	</c:forEach>
</div>