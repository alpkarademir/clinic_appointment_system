<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>||Doktor-Hasta Randevu Sistemi||</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
@import url('https://fonts.googleapis.com/css?family=Josefin+Sans:100,300,400,600,700');

body{
    background: #f2f2f2;
    font-family: 'Josefin Sans', sans-serif;
}

h3{
     font-family: 'Josefin Sans', sans-serif;
}

.box{
    padding:60px 0px;
}

.box-part{
    background:#FFF;
    border-radius:10px;
    padding:60px 10px;
    margin:30px 0px;
}

.box-part:hover{
    background:#4183D7;
}

.box-part:hover .fa , 
.box-part:hover .title , 
.box-part:hover .text ,
.box-part:hover a{
    color:#FFF;
    -webkit-transition: all 1s ease-out;
    -moz-transition: all 1s ease-out;
    -o-transition: all 1s ease-out;
    transition: all 1s ease-out;
}

.text{
    margin:20px 0px;
}

.fa{
     color:#4183D7;
}
</style>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> 

</br></br></br></br></br>

<div class="box">
    <div class="container">
     	<div class="row">
			 
			    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
               
					<div class="box-part text-center">
                        
                        <i class="fa fa-user-md" style="font-size:36px;"></i>
                        
						<div class="title">
							<h3>Doktorlar</h3>
						</div>
                        
<!-- 						<div class="text"> -->
<!-- 							<span>Doktor Kayıt Düzenleme Sistemi için Tıklayınız.</span> -->
<!-- 						</div> -->
                        
						<a href="index" class="btn-sm btn-info" role="button">Enter</a>
                        
					 </div>
				</div>	 
				
				 <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
               
					<div class="box-part text-center">
					    
					    <i class="fa fa-heartbeat" style="font-size:36px;"></i>
                    
						<div class="title">
							<h3>Hastalar</h3>
						</div>
                        
<!-- 						<div class="text"> -->
<!-- 							<span>Hasta Kayıt Düzenleme Sistemi için Tıklayınız.</span> -->
<!-- 						</div> -->
                        
						<a href="index2" class="btn-sm btn-info" role="button">Enter</a>
                        
					 </div>
				</div>	 
				
				 <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
               
					<div class="box-part text-center">
                        
                        <i class="fa fa-hospital-o" style="font-size:36px;"></i>
                        
						<div class="title">
							<h3>Randevu Sistemi</h3>
						</div>
                        
<!-- 						<div class="text"> -->
<!-- 							<span>Randevu Kayıt Düzenleme Sistemi için Tıklayınız.</span> -->
<!-- 						</div> -->
                        
<!-- 						<a href="rand">Enter</a> -->
						<a href="rand" class="btn-sm btn-info" role="button">Enter</a>
                        
					 </div>
				</div>	 
		
		</div>		
    </div>
</div>
</body>
</html>