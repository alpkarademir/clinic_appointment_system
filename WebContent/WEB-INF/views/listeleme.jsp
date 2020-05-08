<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>||Randevu Sistemi Raporlama Sayfası||</title>
		
</head>
	<body body bgcolor="#eeebf2"  >
	<!--------------------------- Raporlama Tablosu----------------------------------->		
 	<div></div>
 	
 	 <div class="container">
    <div class="row col-md-11 col-md-offset-1 custyle">
    <table class="table table-striped custab">
    <thead>
   
   		<form:form method="post"  modelAttribute="listeleme" action="${pageContext.request.contextPath}/rapor">	
				<tr>
					<td><font color="red" >Randevu Numarası :</font></td>
						<td>
							<select name="id">
								<c:forEach items="${listeleme}" var="item">
									<option value="<c:out value="${item.id}" />">
									<c:out value="${item.no}" /></option>
								</c:forEach>
							</select>
						</td>	
				</tr>
				<tr>
							<td>
							  <td><input type="submit" class="btn btn-primary btn-xs pull-left" value="Raporla" /></td>
				</tr>	

				<tr>										
							<td>
							 	<a href="/DokListSis/raporla" class="btn btn-primary btn-xs pull-right">LİSTEYİ RAPORLA</a>
							</td>
				</tr>
   </form:form>  
    </thead>

				
</body>
</html>
