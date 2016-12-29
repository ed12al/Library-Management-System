<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Enter Title:</label>
	<input type='text' class='form-control' id='editBookName' value='${book.title}'>
</div>

<div class='form-group' id='addPickPublisher'>
	<label class='control-label'>Pick a Publisher:</label>
	<select class="form-control" id='editPublisherId'>
		<option>None</option>
		<c:forEach items="${publishers}" var="publisher">
			<option value='${publisher.publisherId}' ${publisher.equals(book.publisher)? "selected" : ""}>${publisher.publisherName}</option>
		</c:forEach>
	</select>
</div>

<div class='form-group'>
	<label class='control-label'>Add a New Publisher</label>
	<input class='form-control' type='text' id='addNewPublisherName' placeholder='Publisher Name'>
	<input class='form-control' type='text' id='addNewPublisherAddress' placeholder='Publiser Address'>
	<input class='form-control' type='text' id='addNewPublisherPhone' placeholder='Publiser Phone'>
	<button type='button' class='btn btn-primary' onclick='updateBookPublisher(2)'>Add Publisher</button>
</div>

<div class='form-group' id='addPickGenres'><label class='control-label'>Pick Genres:</label>
	<select multiple class="form-control" id='editGenreId'>
		<c:forEach items="${genres}" var="genre">
			<option value='${genre.genreId}' ${bookGenres.contains(genre) ? "selected" : ""}>${genre.genreName}</option>
		</c:forEach>
	</select>
</div>

<div class='form-group'><label class='control-label'>Add a New Genre</label>
	<input class='form-control' type='text' id='addNewGenreName' placeholder='Genre Name'>
	<button type='button' class='btn btn-primary' onclick='updateBookGenres(2)'>Add Genre</button>
</div>

<div class='form-group' id='addPickAuthors'><label class='control-label'>Pick Authors:</label>
	<select multiple class="form-control" id='editAuthorId'>
		<c:forEach items="${authors}" var="author">
		<option value='${author.authorId}' ${bookAuthors.contains(author) ? "selected" : ""}>${author.authorName}</option>
		</c:forEach>
	</select>
</div>

<div class='form-group'><label class='control-label'>Add a New Author</label>
	<input class='form-control' type='text' id='addNewAuthorName' placeholder='Author Name'>
	<button type='button' class='btn btn-primary' onclick='updateBookAuthors(2)'>Add Author</button>
</div>

<input type='hidden' id='editBookId' value='${book.bookId}'>