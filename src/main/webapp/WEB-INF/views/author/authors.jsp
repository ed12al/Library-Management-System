<%@include file="../template.html" %>

<script type="text/javascript">
function viewAuthors(pageNumber){
	$.ajax({
		url: "viewauthors",
		type: "GET",
		data: { 
			searchString: $('#searchString').val(),
			pageNo: pageNumber
		}
	}).done(function(response) {
		$("#authorsTable").html(response);
	});
}

function viewAuthor(Id){
	$.ajax({
		url: "viewauthor",
		type: "GET",
		data: { 
			authorId: Id
		}
	}).done(function(response) {
		$("#viewAuthorModalBody").html(response);
	});
}

function editAuthor(Id){
	$.ajax({
		url:"editauthor",
		type: "GET",
		data: {
			authorId: Id
		}
	}).done(function(response) {
		$("#editAuthorModalBody").html(response);
	});
}

function updateAuthor(){
	$.ajax({
		url: "editauthor",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully edited the author");
			viewAuthors(1);
		},
		data: {
			authorId: $('#editAuthorId').val(),
			authorName: $('#editAuthorName').val()
		}
	});
}

function deleteAuthor(Id){
	$("#deleteAuthorId").val(Id);
}

function removeAuthor(){
	$.ajax({
		url: "deleteauthor",
		type: "POST",
		error: function (xhr,status,error) {
			toastr["error"](xhr.responseText);
	    },
		success: function(response){
			toastr["success"]("Successfully deleted the author");
			viewAuthors(1);
		},
		data: {
			authorId: $("#deleteAuthorId").val()
		}
	});
}
function getAddAuthor(){
	$("#addAuthorName").val("");
}
function addAuthor(){
	$.ajax({
		url: "addauthor",
		type: "POST",
		error: function (xhr,status,error){
			toastr["error"](xhr.responseText);
		},
		success: function(response){
			toastr["success"]("Successfully added the author");
			viewAuthors(1);
		},
		data: {
			authorName: $("#addAuthorName").val()
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
			<input type="text" class="form-control" placeholder="Author Name"
				aria-describedby="basic-addon1" name="searchString" id="searchString" onkeyup="viewAuthors(1)">
		</div>
		<button class="btn btn-primary" data-toggle='modal' data-target='#addAuthorModal' onclick='getAddAuthor()'>Add Author</button>	
		<div id="authorsTable"></div>
	</div>
</div>

<div class="modal fade" id="viewAuthorModal" tabindex="-1" role="dialog" aria-labelledby="viewAuthorModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="viewAuthorModalLabel">View Author Detail</h4>
     		</div>
     		<div class="modal-body" id="viewAuthorModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="editAuthorModal" tabindex="-1" role="dialog" aria-labelledby="editAuthorModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="editAuthorModalLabel">Edit Author</h4>
     		</div>
     		<div class="modal-body" id="editAuthorModalBody"></div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateAuthor()">Save</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="deleteAuthorModal" tabindex="-1" role="dialog" aria-labelledby="deleteAuthorModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="deleteAuthorModalLabel">Delete Author</h4>
     		</div>
     		<div class="modal-body" id="deleteAuthorModalBody">
     			<div class='form-group'>
     				<span> Are you sure that you want to delete this author from the database?</span>
     			</div>
     			<input type='hidden' id='deleteAuthorId'>
     		</div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeAuthor()">Delete</button>
      		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="addAuthorModal" tabindex="-1" role="dialog" aria-labelledby="addAuthorModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="addAuthorModalLabel">Add a New Author</h4>
     		</div>
     		<div class="modal-body" id="addAuthorModalBody">
     			<div class="form-group">
     				<label class="control-label">Enter Author Name:</label><input type="text" class="form-control" id="addAuthorName">
     			</div>
			</div>
     		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addAuthor()">Add</button>
      		</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	window.onload = viewAuthors(1);
</script>