<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Enter Borrower Name to Edit:</label>
	<input class='form-control' type='text' id='editBorrowerName' value='${borrower.name}'>
</div>

<div class='form-group'>
	<label class='control-label'>Enter Borrower Address to Edit:</label>
	<input class='form-control' type='text' id='editBorrowerAddress' value='${borrower.address}'>
</div>

<div class='form-group'>
	<label class='control-label'>Enter Borrower Phone to Edit:</label>
	<input class='form-control' type='text' id='editBorrowerPhone' value='${borrower.phone}'>
</div>

<input type='hidden' id='editCardNo' value='${borrower.cardNo}'>