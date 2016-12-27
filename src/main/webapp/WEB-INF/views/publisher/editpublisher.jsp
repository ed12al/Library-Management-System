<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Enter Publisher Name to Edit:</label>
	<input class='form-control' type='text' id='editPublisherName' value='${publisher.publisherName}'>
</div>

<div class='form-group'>
	<label class='control-label'>Enter Publisher Address to Edit:</label>
	<input class='form-control' type='text' id='editPublisherAddress' value='${publisher.publisherAddress}'>
</div>

<div class='form-group'>
	<label class='control-label'>Enter Publisher Phone to Edit:</label>
	<input class='form-control' type='text' id='editPublisherPhone' value='${publisher.publisherPhone}'>
</div>

<input type='hidden' id='editPublisherId' value='${publisher.publisherId}'>