<%@include file="template.html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

function viewBooks(pageNumber){
	$.ajax({
		url: "borrower/viewbooks",
		type: "GET",
		data: { 
			searchString: $('#searchString').val(),
			pageNo: pageNumber
		}
	}).done(function(response) {
		$("#booksTable").html(response);
	});
}

function viewBook(Id){
	$.ajax({
		url: "borrower/viewbook",
		type: "GET",
		data: { 
			bookId: Id
		}
	}).done(function(response) {
		$("#viewBookModalBody").html(response);
	});
}

function borrowBook(Id){
	$.ajax({
		url: "borrower/borrowbook",
		type: "GET",
		data: { 
			bookId: Id
		}
	}).done(function(response) {
		$("#borrowBookModalBody").html(response);
	});
}

function borrowedBook(branchId, bookId){
	$.ajax({
		url: "borrower/borrowbook",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully borrowed a book");
			borrowBook(bookId);
		},
		data: {
			branchId: branchId,
			bookId: bookId,
			cardNo: $("#cardNoId").val()
		}		
	})
}

function returnBooks(){
	$.ajax({
		url: "borrower/returnbooks",
		type: "GET",
		data: { 
			cardNo: $("#cardNoId").val()
		}
	}).done(function(response) {
		$("#returnBookModalBody").html(response);
	});
}

function returnedBook(Id){
	$.ajax({
		url: "borrower/returnbooks",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully returned a book");
			returnBooks();
		},
		data: {
			loanId: Id
		}		
	})
}

</script>

<div class="container theme-showcase" role="main">
	<div class="jumbotron">
		<h1>GCIT Library Management System</h1>
		<p>Welcome to GCIT Library Management System. Have Fun Shopping!</p>
		<h3>Hello ${borrower.name}! What do you want to do?</h3>
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Book Title"
				aria-describedby="basic-addon1" name="searchString" id="searchString" onkeyup="viewBooks(1)">	
		</div>
		<button class="btn btn-primary" data-toggle='modal' data-target='#returnBookModal' onclick='returnBooks()'>Return</button>	
		<div id="booksTable"></div>
	</div>
</div>

<div class="modal fade" id="viewBookModal" tabindex="-1" role="dialog" aria-labelledby="viewBookModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="viewBookModalLabel">View Book Detail</h4>
     		</div>
     		<div class="modal-body" id="viewBookModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="borrowBookModal" tabindex="-1" role="dialog" aria-labelledby="borrowBookModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="borrowBookModalLabel">View Book Availability</h4>
     		</div>
     		<div class="modal-body" id="borrowBookModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="returnBookModal" tabindex="-1" role="dialog" aria-labelledby="returnBookModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="returnBookModalLabel">Return Books</h4>
     		</div>
     		<div class="modal-body" id="returnBookModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      		</div>
		</div>
	</div>
</div>

<input type="hidden" id="cardNoId" value="${borrower.cardNo}">

<script type="text/javascript">
	window.onload = viewBooks(1);
</script>