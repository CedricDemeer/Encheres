<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Personnal CSS-->
<link rel="stylesheet" href="css/style.css">

<!--icons-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<title>ENI-Encheres : Liste des enchères</title>
</head>
<body>

 <header>
            
	<%@ include file="navbar.html"%>
        </header>



	<form class="form-register needs-validation" novalidate method="post"
		action="" enctype="multipart/form-data">
		 <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
		<div class="row">
			<div class="col-md-9">
				<label for="image">Ajouter une image à votre article <span
					class="text-muted">(Optionel mais fortement conseillé)</span></label> <input
					type="file" class="form-control" id="photo" name="photo"
					accept="image/png, image/jpeg">
			</div>
		</div>
		 <input type="submit" />
	</form>
	
	 




</body>
</html>