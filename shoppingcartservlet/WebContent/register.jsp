<!DOCTYPE html>
<%@page import="java.util.ResourceBundle"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="mytags" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<style>
	h1 {
		text-align: center;
		margin-top:50px;
		color:white;
	}
	body{
  		 background: linear-gradient(to right, #44318d 0%, #cc99ff 100%);
	}
	.container{
	width:300px;
	}
</style>
</head>
<body>
	<h1>Registration form</h1>
	<form action="register.do" method="post">
	<input type="hidden" name="formid" value="register">
	<div class="container">
	<div class="mx-auto">
  	<div class="form-group row">
		<label for="username" class="col-form-label"><mytags:bundle key="username"/>:<input type="text" name="uname" class="form-control"></label>
	</div>
	<div class="form-group row">
		<label for="passwordinput" class="col-form-label"><mytags:bundle key="password" />:<input type="password" name="upass" class="form-control"></label>
	</div>
	<div class="row">
	<button type="submit" class="btn btn-secondary">Register</button>
	</div>
	</div>
	</div>
	</form>
</body>
</html>