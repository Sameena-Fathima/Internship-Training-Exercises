<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</head>
<style>
	h1 {
		text-align: center;
		margin-top:50px;
		color:white;
	}
	body{
  		 background: linear-gradient(to right, #44318d 0%, #cc99ff 100%);
  		 font-family: "Poppins", sans-serif;
	}
	.container{
	width:300px;
	}
	
</style>
<body>
	<h1>Select Language</h1>
	
	<form action="lang.do;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="lang">
	<div class="container">
	<div class="mx-auto">
  	<div class="row">
	<select class="form-control col align-self-center mt-3" aria-label=".form-select-lg example" name="language">
	
  		<option value="ta" selected>Tamil</option>
			<option value="te">Telugu</option>
			<option value="hi">Hindi</option>
	</select>
	</div>
	<div class="row">
	<button type="submit" class="btn btn-secondary col align-self-center mt-3">Select</button>
	</div>
	</div>
	</div>
	</form>
</body>
</html>