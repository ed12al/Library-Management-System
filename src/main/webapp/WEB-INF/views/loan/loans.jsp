<%@include file="../template.html" %>

<script type="text/javascript">
function viewLoans(pageNumber){
	$.ajax({
		url: "viewloans",
		type: "GET",
		data: { 
			searchString: $('#searchString').val(),
			pageNo: pageNumber,
			seeAll: $('#seeAll').is(":checked")
		}
	}).done(function(response) {
		$("#loansTable").html(response);
	});
}

function updateLoan(Id){
	$.ajax({
		url: "editloan",
		type: "POST",
		data: {
			loanId: Id, 
			diff: $('#editDiff'+Id).val()
		},
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully edited the loan");
			viewLoans(1);
		}
	});
}

function deleteLoan(Id){
	$("#deleteLoanId").val(Id);
}

function removeLoan(){
	$.ajax({
		url: "deleteloan",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully deleted the loan");
			viewLoans(1);
		},
		data: {
			loanId: $("#deleteLoanId").val()
		}
	});
}
</script>

<div class="container theme-showcase" role="main">
	<div class="jumbotron">
		<h1>GCIT Library Management System</h1>
		<p>Welcome to GCIT Library Management System. Have Fun Shopping!</p>
		<h3>Hello Administrator! What do you want to do?</h3>
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Search"
				aria-describedby="basic-addon1" name="searchString" id="searchString" onkeyup="viewLoans(1)">
		</div>
		<div class="checkbox">
    		<label>
      			<input type="checkbox" id="seeAll" onclick='viewLoans(1)'> See All
    		</label>
  		</div>	
		<div id="loansTable"></div>
	</div>
</div>

<div class="modal fade" id="deleteLoanModal" tabindex="-1" role="dialog" aria-labelledby="deleteLoanModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="deleteLoanModalLabel">Delete Loan</h4>
     		</div>
     		<div class="modal-body" id="deleteLoanModalBody">
     			<div class='form-group'>
     				<span> Are you sure that you want to delete this loan from the database?</span>
     			</div>
     			<input type='hidden' id='deleteLoanId'>
     		</div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeLoan()">Delete</button>
      		</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	window.onload = viewLoans(1);
</script>