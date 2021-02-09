<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
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

<title>ENI-Encheres : Modifier mon profil</title>
</head>
<body>
	<div class="container-fluid">
		<!--emptyHeader-->
		<header>
			 <%@ include file="navbar.jsp"%>
		</header>

		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Modifier du profil</h1>
				<img class="mb-4 large-icon rounded-circle" src="images/user.svg"
					alt="">
			</div>

			<!--formulaire-->
			<form class="form-register needs-validation" novalidate method="post"
				action="">
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="pseudo">Pseudo</label> <input type="text"
							class="form-control" id="pseudo" name="pseudo" placeholder=""
							maxlength="30" required value="${sessionScope.user.pseudo}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="lastname">Nom</label> <input type="text"
							class="form-control" id="lastname" name="lastname" placeholder=""
							value="${sessionScope.user.nom}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstname">Prénom</label> <input type="text"
							class="form-control" id="firstname" name="firstname"
							placeholder="" value="${sessionScope.user.prenom}" maxlength="30"
							required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="you@example.com" value="${sessionScope.user.email}"
							maxlength="20" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="phone">Téléphone <span class="text-muted">(Optional)</span></label>
						<input type="text" class="form-control" id="phone" name="phone"
							placeholder="" value="${sessionScope.user.telephone}"
							maxlength="15">
					</div>
					<div class="col-md-6 mb-3">
						<label for="street">Rue</label> <input type="text"
							class="form-control" id="street" name="street" placeholder=""
							value="${sessionScope.user.rue}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="zipcode">Code postal</label> <input type="number"
							class="form-control" id="zipcode" name="zipcode" placeholder=""
							min="01000" max="99999" value="${sessionScope.user.codePostal}"
							required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="city">Ville</label> <input type="text"
							class="form-control" id="city" name="city" placeholder=""
							maxlength="30" value="${sessionScope.user.ville}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="password">Ancien mot de passe</label> <input
							type="password" class="form-control" id="oldpassword"
							name="oldpassword" placeholder="" minlength="6" maxlength="30"
							value="${sessionScope.user.motDePasse}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="password">Nouveau mot de passe</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="" minlength="6" maxlength="30"
							value="${sessionScope.user.motDePasse}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="confirm_password">Confirmation</label> <input
							type="password" class="form-control" id="confirm_password"
							name="confirm_password" placeholder=""
							value="${sessionScope.user.motDePasse}" required>
						<div class="invalid-feedback">Ce champ est invalide ou les
							mots de passe sont différents !</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 mb-3">
						<button name="update" class="btn btn-primary btn-lg btn-block"
							type="submit">Enregistrer ces modifications</button>
					</div>
				</div>
			</form>
			<form action="<%=request.getContextPath()%>/SuppProfil" method="post">
				<input class="btn btn-danger btn-lg btn-block" type="submit"
					name="buttonDelete" value="Supprimer mon compte" />

			</form></div>


	<c:if test="${!empty erreurs}">
				<ul>
		<c:forEach var="erreur" items="${erreurs}">
			<li>${erreur}</li>
		</c:forEach>
	</ul>
	
	</c:if>

		</main>
		<!--footer-->
		<footer class="border-top text-center align-bottom">
			<div class="mt-3">
				<img class="small-icon" src="images/ateni.svg" alt="Eni Ecole">
				<small class="d-block text-muted">&copy; ENI Ecole 2020</small>
				<div>
					Icons made by <a href="https://www.flaticon.com/authors/freepik"
						title="Freepik">Freepik</a> from <a
						href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
				</div>
			</div>
		</footer>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';

        window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');

        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                //validation du mot de passe
                var password = document.getElementById("password")
                , confirm_password = document.getElementById("confirm_password");
                if(password.value != confirm_password.value) {
                    confirm_password.setCustomValidity("Les mots de passe sont différents");
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    confirm_password.setCustomValidity('');
                }
                //validations des saisies obligatoires
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });            
    }, false);
    })();
    </script>
</body>

</html>