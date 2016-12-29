<%@include file="template.html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="POST" action="/lms/borrower">
	<div class="form-group">
		<label class="control-label">Please Enter Your Card Number:</label>
		<c:if test="${not empty error}">
			<p class="text-danger">${error}</p>
		</c:if>
		<input class="form-control" name="cardNo" type="text">
		<input class="btn btn-primary" type="submit" value="Submit">
	</div>
</form>