<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="utf-8">
<title>||Doktor Yönetim Sayfası||</title>
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
        $table = $panel.find('.doctor'),
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
<script>function fonkran(){window.location.replace("/DokListSis/rand");}</script>
<script>function fonks(){window.location.replace("logout");}</script>

	<center>
	<div class="container">
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">Doktorlar</h3>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filtrele</button>
<!--                       <a href="logout"><label style="color:#4d0000"><small>Logout</small></label> -->
<!--           			<span class="glyphicon glyphicon-log-out" style="color:#4d0000"></span> -->
<!--         			</a> -->
					 <button type= "button" onclick="fonks()" class="btn btn-default btn-sm">
					  <span class="glyphicon glyphicon-log-out"></span> Logout
					  </button>
                </div>
            </div>
            <table class="doctor">
                <thead>
                    <tr class="filters">
                        <th><input type="text" class="form-control" placeholder="Doktor No" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Adı-Soyadı" disabled></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
            <c:forEach items="${listeled}" var="i">
				<TR>
				<td><c:out value="${i.no}" /></td>
				<td><c:out value="${i.name}" /></td>
      	      	<form:form method="get" modelAttribute="doctor" action="sil/${i.id}">
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

<!-- 		<table border="1" BGCOLOR="#D6CBC5" width="50%"> -->
<!-- 			<tr> -->
<!-- 				<th>Hasta No</th> -->
<!-- 				<th>Ad</th> -->
<!-- 			</tr> -->

<%-- 			<c:forEach items="${listeleh}" var="i"> --%>
<!-- 				<TR> -->
<%-- 				<td bgcolor="#D6CBC5"><c:out value="${i.no}" /></td> --%>
<%-- 				<td bgcolor="#D6CBC5"><c:out value="${i.name}" /></td> --%>
<%-- 				<td bgcolor="#D6CBC5"><a href="sil2/${i.id}">Sil</a></td> --%>
<!-- 				</TR> -->
<%-- 			</c:forEach> --%>


<!-- 		</table> -->


		&nbsp; &nbsp;

				<fieldset>
				<legend>Doktor Ekle</legend>
				<form:form class="form-inline" method="POST" modelAttribute="doctor" action="ekleme">
				    <div class="form-group">
				    <label>Doktor Adı-Soyadı:</label>
				      <input type="text" name= "name" class="form-control" placeholder="Ad-Soyad">
				      </div>
				       &nbsp;
				    <div class="form-group">
				        <label>Doktor No:</label>
				      <input type="text" name= "no" class="form-control" placeholder="Numara">
				    </div>
				    <td><button type="submit" class="btn btn-primary">Ekle</button></td>
				  </form:form>	
				</fieldset>

		&nbsp; &nbsp;

<hr>
   <fieldset>
   <legend>Doktor Güncelle</legend>
   <form:form method="post" modelAttribute="doctor" action="guncelleme">
				<div class="container">
<!--   						<div class="row"> -->
<!--    					 	<div class="col-sm-12"> -->
   					 	<div class="form-group form-inline">
   					 	<label>Doktor:</label>
   					 	 &nbsp;
      					<select class="form-control" name="id">
						<c:forEach items="${listeled}" var="item">
							<option value="<c:out value="${item.id}" />"><c:out
									value="${item.name}" /></option>
						</c:forEach>
    				  	</select>
    				  	</div>
    				  	</div>
<!--    						</div> -->
						<td><div class="form-inline">
				    	<label>Ad-Soyad:</label>
				      <input type="text" name= "name" class="form-control" placeholder="Doktor Adı-Soyadı">
				      </div>
				      </td>
				      <br/>
						<td><div class="form-inline">
				    	<label>Doktor No:</label>
				      <input type="text" name= "no" class="form-control" placeholder="Doktor Numarası">
				    </div>
				    </td>
				    <br/>
				   <tr><td><button type="submit" class="btn btn-success">Güncelle</button></td>
				  </tr>  
   </form:form>
   </br>
   				   <td><button onclick="fonkhas()" class="btn btn-info pull-left">Hasta Kayıt</button>
   				   &nbsp; &nbsp;
   				   <button onclick="fonkran()" class="btn btn-info pull-right">Randevu Kayıt</button></td>
   				   &nbsp;
   
   </fieldset>
   <hr>
	</center>
</body>
</html>