<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.ItemMasterDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cosmetics</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<style>
body{
  		 background: linear-gradient(to right, #44318d 0%, #cc99ff 100%);
  		 
	}
</style>
</head>
<body>
	<jsp:include page="logout.jsp"></jsp:include>
	<div class="row justify-content-center">
	 <h1>Cosmetics</h1>
	</div>
	<form action="shop.do;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="invoice">
	<input type="hidden" name="nextid" value="shop3">
	<input type="hidden" name="category" value="Cosmetics"> 
	<div class="row justify-content-center">
    <div class="col ml-3 mr-3">
	<table class="table table-striped table-dark" style="width:100%">
  	<thead>
  	 <tr>
      <th scope="col"></th>
      <th scope="col">Item Name</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
       <th scope="col">Image</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach items="${items}" var="item">
	<tr>
	<td><input class="form-control" type="checkbox" name="choice" value=<c:out value='${item.getItemno()}' />></td>
	<td>${item.getItemname()}</td> 
	<td><select class="form-control" name=<c:out value='${item.getItemno()}' />>
	<option value="0">Select quantity</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	</select></td>
	<td><span>${item.getPrice()}</span></td>
	<td><img src=<c:out value='${item.getImage()}'/> width="75" height="75" class="img-thumbnail"></td>
	</tr>
	</c:forEach>
	</tbody>
		</table>
		</div>
		</div>
		 <div class="form-group">
                <button class="btn btn-success btn-lg float-right mr-3" type="submit">Next
                </button>
            </div>
	</form>
</body>
</html>