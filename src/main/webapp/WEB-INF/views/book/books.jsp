<%@include file="../template.html" %>

<script type="text/javascript">
function viewBooks(pageNumber){
	$.ajax({
		url: "viewbooks",
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
		url: "viewbook",
		type: "GET",
		data: { 
			bookId: Id
		}
	}).done(function(response) {
		$("#viewBookModalBody").html(response);
	});
}

function editBook(Id){
	$.ajax({
		url:"editbook",
		type: "GET",
		data: {
			bookId: Id
		}
	}).done(function(response) {
		$("#editBookModalBody").html(response);
	});
}

function updateBook(){
	var editGenreIds = $('#editGenreId option:selected').map(function(){ return $(this).val(); }).get();
	var editAuthorIds = $('#editAuthorId option:selected').map(function(){ return $(this).val(); }).get();
	$.ajax({
		url: "editbook",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully edited the book");
			viewBooks(1);
		},
		data: {
			bookId: $('#editBookId').val(),
			title: $('#editBookName').val(),
			publisherId: $("#editPublisherId option:selected").val(),
			genreIds: editGenreIds,
			authorIds: editAuthorIds
		}
	});
}

function deleteBook(Id){
	$("#deleteBookId").val(Id);
}

function removeBook(){
	$.ajax({
		url: "deletebook",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully deleted the book");
			viewBooks(1);
		},
		data: {
			bookId: $("#deleteBookId").val()
		}
	});
}

function getAddBook(){
	$.ajax({
		url: "addbook",
		type: "GET"
	}).done(function(response){
		$('#addBookModalBody').html(response);
	});
}

function addBook(){
	var addGenreIds = $('#addGenreId option:selected').map(function(){ return $(this).val(); }).get();
	var addAuthorIds = $('#addAuthorId option:selected').map(function(){ return $(this).val(); }).get();
	$.ajax({
		url: "addbook",
		type: "POST",
		error: function (xhr,status,error){
			toastr["error"](xhr.responseText);
		},
		success: function(response){
			toastr["success"]("Successfully added the book");
			viewBooks(1);
		},
		data: {
			title: $("#addBookName").val(),
			publisherId: $("#addPublisherId option:selected").val(),
			genreIds: addGenreIds,
			authorIds: addAuthorIds
		}
	})
}

function updateBookPublisher(flag){
	$.ajax({
		url: "addpublisher",
		type: "POST",
		error: function (xhr,status,error){
			toastr["error"](xhr.responseText);
		},
		success: function(response){
			toastr["success"]("Successfully added the publisher");
			var publisherName = $("#addNewPublisherName").val();
			if(flag === 1){
				$('#addPublisherId').append("<option value="+response+">"+publisherName+"</option>");
			}else if(flag === 2){
				$('#editPublisherId').append("<option value="+response+">"+publisherName+"</option>");
			}
			publisherName: $("#addNewPublisherName").val("");
			publisherPhone: $('#addNewPublisherPhone').val("");
			publisherAddress: $('#addNewPublisherAddress').val("");
		},
		data: {
			publisherName: $("#addNewPublisherName").val(),
			publisherPhone: $('#addNewPublisherPhone').val(),
			publisherAddress: $('#addNewPublisherAddress').val()			
		}
	})
}

function updateBookGenres(flag){
	$.ajax({
		url: "addgenre",
		type: "POST",
		error: function (xhr,status,error){
			toastr["error"](xhr.responseText);
		},
		success: function(response){
			toastr["success"]("Successfully added the genre");
			var genreName = $("#addNewGenreName").val();
			if(flag === 1){
				$('#addGenreId').append("<option value="+response+">"+genreName+"</option>");
			}else if(flag === 2){
				$('#editGenreId').append("<option value="+response+">"+genreName+"</option>");
			}
			genreName: $("#addNewGenreName").val("");
		},
		data: {
			genreName: $("#addNewGenreName").val()		
		}
	})
}

function updateBookAuthors(flag){
	$.ajax({
		url: "addauthor",
		type: "POST",
		error: function (xhr,status,error){
			toastr["error"](xhr.responseText);
		},
		success: function(response){
			toastr["success"]("Successfully added the author");
			var authorName = $("#addNewAuthorName").val();
			if(flag === 1){
				$('#addAuthorId').append("<option value="+response+">"+authorName+"</option>");
			}else if(flag === 2){
				$('#editAuthorId').append("<option value="+response+">"+authorName+"</option>");
			}
			authorName: $("#addNewAuthorName").val("");
		},
		data: {
			authorName: $("#addNewAuthorName").val()		
		}
	})
}
</script>

<div class="container theme-showcase" role="main">
	<div class="jumbotron">
		<h1>GCIT Library Management System</h1>
		<p>Welcome to GCIT Library Management System. Have Fun Shopping!</p>
		<h3>Hello Administrator! What do you want to do?</h3>
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Book Name"
				aria-describedby="basic-addon1" name="searchString" id="searchString" onkeyup="viewBooks(1)">
		</div>
		<button class="btn btn-primary" data-toggle='modal' data-target='#addBookModal' onclick='getAddBook()'>Add Book</button>	
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

<div class="modal fade" id="editBookModal" tabindex="-1" role="dialog" aria-labelledby="editBookModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="editBookModalLabel">Edit Book</h4>
     		</div>
     		<div class="modal-body" id="editBookModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateBook()">Save</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="deleteBookModal" tabindex="-1" role="dialog" aria-labelledby="deleteBookModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="deleteBookModalLabel">Delete Book</h4>
     		</div>
     		<div class="modal-body" id="deleteBookModalBody">
     			<div class='form-group'>
     				<span> Are you sure that you want to delete this book from the database?</span>
     			</div>
     			<input type='hidden' id='deleteBookId'>
     		</div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeBook()">Delete</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="addBookModal" tabindex="-1" role="dialog" aria-labelledby="addBookModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="addBookModalLabel">Add a New Book</h4>
     		</div>
     		<div class="modal-body" id="addBookModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addBook()">Add</button>
      		</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	window.onload = viewBooks(1);
</script>