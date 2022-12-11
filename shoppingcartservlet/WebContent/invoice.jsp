<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.ItemMasterDTO" %>
    <%@ page import="java.util.Set" %>
    <%@ page import="java.util.HashSet" %>
     <%@ page import="java.util.HashMap" %>
         <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice</title>
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
<div class="container">
<div class="row justify-content-center">
    <div class="col-auto">
    <h1>Invoice</h1>
</div>
</div>
<div class="d-flex justify-content-between">
      <div>
         Customer Name :${totaldetails.getCustomermasterdto().getCustname()}
      </div>
      <div>
        Customer Email: ${totaldetails.getCustomermasterdto().getCustemail()}
      </div>
 </div>
 <div class="d-flex justify-content-between">
      <div>
        Customer Phone: <span>${totaldetails.getCustomermasterdto().getCustphoneno()}</span>
      </div>
      <div>
        Customer Address: <span>${totaldetails.getCustomermasterdto().getCustaddress()}</span>
      </div>
 </div>

	<div class="row justify-content-center">
    <div class="col-auto">
	<table border="1" class="table table-bordered mt-3">
	<tr>
	<th>Item No</th>
	<th>Item Name</th>
	<th>Quantity</th>
	<th>Price</th>
	<th>Unit</th>
	</tr>

	<c:forEach items="${totaldetails.getItemdetails()}" var="item">
	<tr>
	<td>${item.getItemno()}</td> 
	<td>${item.getItemname()}</td>
	<td>${item.getItemqty()}</td>
	<td>${item.getItemprice()}</td>
	<td>${item.getItemunit()}</td>
	</tr>
	</c:forEach>
		</table>
		</div>
		</div>
	<div class="row justify-content-center">
	<form action="pdf.do;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="pdf">
	<div class="col-auto"><input class="btn btn-primary" type="submit" value="Create PDF"></div></form>
	<form action="excel.do;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="excel">
	<div class="col-auto"><input class="btn btn-primary ml-3" type="submit" value="Create Excel"></div></form>
	</div>
	<div class="row justify-content-center">
	<form action="email.do;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="email">
	<div class="col-auto"><input class="btn btn-primary mt-3" type="submit" value="Send Email"></div></form>
	<form action="sms.do;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="sms">
	<div class="col-auto"><input class="btn btn-primary ml-3 mt-3" type="submit" value="Send SMS"></div></form></div>
	</div>
</body>
</html>