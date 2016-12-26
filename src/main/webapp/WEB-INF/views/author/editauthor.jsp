<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Enter Author Name to Edit:</label>
	<input class='form-control' type='text' id='editAuthorName' value='${author.authorName}'>
</div>
<input type='hidden' id='editAuthorId' value='${author.authorId}'>