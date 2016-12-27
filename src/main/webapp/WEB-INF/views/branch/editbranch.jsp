<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='form-group'>
	<label class='control-label'>Enter Branch Name to Edit:</label>
	<input class='form-control' type='text' id='editBranchName' value='${branch.branchName}'>
</div>

<div class='form-group'>
	<label class='control-label'>Enter Branch Address to Edit:</label>
	<input class='form-control' type='text' id='editBranchAddress' value='${branch.branchAddress}'>
</div>

<input type='hidden' id='editBranchId' value='${branch.branchId}'>