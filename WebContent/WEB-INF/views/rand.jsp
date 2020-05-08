<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="utf-8">
<title>||Randevu Yönetim Sayfası||</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.filterable {
    margin-top: 15px;
}
.filterable .panel-heading .pull-right {
    margin-top: -15px;
}
.filterable .filters input[disabled] {
    background-color: transparent;
    border: none;
    cursor: auto;
    box-shadow: none;
    padding: 10px;
    height: auto;
}
.filterable .filters input[disabled]::-webkit-input-placeholder {
    color: #333;
}
.filterable .filters input[disabled]::-moz-placeholder {
    color: #333;
}
.filterable .filters input[disabled]:-ms-input-placeholder {
    color: #333;
}
</style>
</head>
<body>
<script>
$(document).ready(function(){
    $('.filterable .btn-filter').click(function(){
        var $panel = $(this).parents('.filterable'),
        $filters = $panel.find('.filters input'),
        $tbody = $panel.find('.table tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.randevu'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
        }
    });
});
</script>
<script>function fonkhas(){window.location.replace("/DokListSis/index2");}</script>
<script>function fonkdok(){window.location.replace("/DokListSis/index");}</script>
<script>function fonks(){window.location.replace("logout");}</script>

	<center>
	
	<div class="container">
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">Randevular</h3>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filtrele</button>
                 	<button type= "button" onclick="fonks()" class="btn btn-default btn-sm">
					  <span class="glyphicon glyphicon-log-out"></span> Logout
					  </button>
                </div>
            </div>
            <table class="randevu">
                <thead>
                    <tr class="filters">
                        <th><input type="text" class="form-control" placeholder="Randevu No" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Randevu Saati" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Doktor Adı" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Hasta Adı" disabled></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
            <c:forEach items="${listelex}" var="i">
				<TR>
				<td><c:out value="${i.no}" /></td>
				<td><c:out value="${i.time}" /></td>
				<td><c:out value="${i.doctor.name}" /></td>
				<td><c:out value="${i.patient.name}" /></td>
      	      	<form:form method="get" modelAttribute="doctor" action="sil3/${i.id}">
      	      	<td><button type="submit" class="btn btn-danger">Sil</button></td>
      	      	</form:form>
				</TR>
			</c:forEach>
                        
                    </tr>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


		&nbsp; &nbsp;

		<fieldset>
				<legend>Randevu Ekle</legend>
				<form:form class="form-inline" method="POST" modelAttribute="randevu" action="ekleme3">
				    <div class="form-group">
				    <label>Randevu No:</label>
				      <input type="text" name= "no" class="form-control" placeholder="Randevu Numarası">
				      </div>
				       &nbsp;
				    <div class="form-group">
				        <label>Randevu Tarihi:</label>
				      <input type="text" name= "time" class="form-control" placeholder="Tarih">
				    </div>
				     <br/>  <br/>
				    	<div class="container">
<!--   						<div class="row"> -->
<!--    					 	<div class="col-sm-12"> -->
   					 	<div class="form-group form-inline">
   					 	<label>Doktor:</label>
   					 	 &nbsp;
      					<select class="form-control" name="dokid">
						<c:forEach items="${listeled}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.name}" /></option>
						</c:forEach>
    				  	</select>
    				  	&nbsp;
    				  	<label>Hasta:</label>
   					 	 &nbsp;
      					<select class="form-control" name="patid">
						<c:forEach items="${listeleh}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.name}" /></option>
						</c:forEach>
    				  	</select>
    				  	</div>
    				  	</div>
					<tr>

					</tr>
					 <br/>
					<tr>
						<td></td>
				    <td><button type="submit" class="btn btn-primary">Ekle</button></td>
				  </form:form>	
				</fieldset>

		&nbsp; &nbsp;

<hr>
   <fieldset>
   <legend>Randevu Güncelle</legend>
   <form:form method="post" modelAttribute="randevu" action="guncelleme3">
				<div class="container">
<!--   						<div class="row"> -->
<!--    					 	<div class="col-sm-12"> -->
   					 	<div class="form-group form-inline">
   					 	<label>Randevu No:</label>
   					 	 &nbsp;
      					<select class="form-control" name="id">
						<c:forEach items="${listelex}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.no}" /></option>
						</c:forEach>
    				  	</select>
    				  	 &nbsp;
    				  	<label>Doktor:</label>
   					 	 &nbsp;
      					<select class="form-control" name="dokid">
						<c:forEach items="${listeled}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.name}" /></option>
						</c:forEach>
    				  	</select>
    				  	 &nbsp;
    				  	<label>Hasta:</label>
   					 	 &nbsp;
      					<select class="form-control" name="patid">
						<c:forEach items="${listeleh}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.name}" /></option>
						</c:forEach>
    				  	</select>
    				  	</div>
    				  	</div>
<!--    						</div> -->
						<td><div class="form-inline">
				    	<label>Randevu Tarihi:</label>
				      <input type="text" name= "time" class="form-control" placeholder="Randevu Tarihi">
				      &nbsp;
				      <label>Randevu No:</label>
				      <input type="text" name= "no" class="form-control" placeholder="Randevu Numarası">
				      </div>
				      </td>
				    <br/>
				   <tr><td><button type="submit" class="btn btn-success">Güncelle</button></td>
				  </tr>  
   </form:form>
   
   <hr>
   <fieldset>
   <legend>Randevu Listele</legend>
			   <form:form method="post"  modelAttribute="randevu" action="${pageContext.request.contextPath}/rapor">	
						<div class="container">
   					 	<div class="form-group form-inline">
   					 	<label>Randevu No:</label>
   					 	 &nbsp;
      					<select class="form-control" name="id">
						<c:forEach items="${listelex}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.no}" /></option>
						</c:forEach>
    				  	</select>
							&nbsp; &nbsp;
							<tr>
							<td>
							<td><input type="submit" class="btn btn-primary btn-xs" value="Filtreli Raporlama" /></td>
							</td>
							</tr>		
							&nbsp;
						<tr>				
						<td>
						<a href="/DokListSis/raporla" class="btn btn-primary btn-xs">Tam Liste Raporlama</a>
						</td>
						</tr>
						</form:form>  
   					</br></br>
   					<td><button onclick="fonkhas()" class="btn btn-info pull-left">Hasta Kayıt</button>
   				   &nbsp; &nbsp;
   				   <button onclick="fonkdok()" class="btn btn-info pull-right">Doktor Kayıt</button></td>
   				   &nbsp;
   </fieldset>
   <hr>
	</center>
</body>
</html>