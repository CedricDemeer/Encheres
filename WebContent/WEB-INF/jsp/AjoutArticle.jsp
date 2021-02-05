<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
    <!-- Personnal CSS-->
    <link rel="stylesheet" href="css/style.css">

    <!--icons-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <title>ENI-Encheres : Ajouter un article</title>
</head>
<body>
 <div class="container-fluid">
        <!--emptyHeader-->
        <header>
          <%@ include file="navbar.html"%>
        </header>
        
        <main>
            <!--title-->
            <div class="mx-auto text-center">
                <h1>Ajouter un article</h1>
                <img class="mb-4 large-icon rounded-circle" src="images/article.svg" alt="">
            </div>
            
            <form class="form-register needs-validation" novalidate method="post" action="" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-12">
                        <label for="nomArticle">Nom de l'article <span
							class="text-muted">*</span></label>
                        <input type="text" class="form-control" id="nomArticle" name="nomArticle" maxlength="30" required>
                        <div class="invalid-feedback">
                            Il manque le nom de votre article !
                        </div>
                    </div>
                        </div>
                <div class="row">
                    <div class="col-md-12">
                        <label for="description">Décrivez votre article <span
							class="text-muted">*</span></label>
                        <input type="text" class="form-control" id="description" name="description" placeholder="Décrivez votre article le plus précisément possible." maxlength="60" required>
                        <div class="invalid-feedback">
                            Il vous faut donner une description à votre article !
                        </div>
                    </div>
                </div>
				<div class="row">
					<div class="col-md-9">
						<label for="image">Ajouter une image à votre article <span
							class="text-muted">(Optionel mais fortement conseillé)</span></label> <input
							type="file" class="form-control" id="photo" name="photo"
							accept="image/png, image/jpeg">
					</div>
				</div>
				<div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="dateDebutEncheres">Date de début de l'enchère <span
							class="text-muted">*</span></label>
                        <input type="date" class="form-control" id="dateDebutEncheres" name="dateDebutEncheres" value="" required>
                        <div class="invalid-feedback">
                            Veuillez sélectionner une date 
                        </div>
                    </div>
                
                    <div class="col-md-6 mb-3">
                        <label for="dateFinEncheres">Date de fin de l'enchère <span
							class="text-muted">*</span></label>
                        <input type="date" class="form-control" id="dateFinEncheres" name="dateFinEncheres"  value="" required>
                        <div class="invalid-feedback">
                            Veuillez sélectionner une date 
                        </div>
                    </div>
                </div>

				<div class="row">
					
					 <div class="col-md-6 mb-3">
				    <label for="categorie">Catégorie de votre article <span
							class="text-muted">*</span></label>
				    <br/>
				        <select name="Categorie">
				        	<c:forEach var="c" items="${listCategories}">
				      			<option value="${c.libelle }" >${c.libelle }</option>
				        	</c:forEach>
				        	
				        	<!--  <option value="Ameublement" selected="selected">Ameublement</option>
				        	<option value="Informatique">Informatique</option>
				        	<option value="SportEtLoisirs">Sports & Loisirs</option>
				        	<option value="Vetement">Vêtements</option>-->
				        </select>
				        <div class="invalid-feedback">
				            Ce champ est invalide !
				        </div>
				    </div>
				    <div class="col-md-6 mb-3">
						<label for="prixInitial">Prix de départ <span
							class="text-muted">(Optionel)</span></label> <input type="number"
							class="form-control" id="prixInitial" name="prixInitial"
							placeholder="" value="" maxlength="15">
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 mb-3">
						<label for="street">Rue <span
							class="text-muted">*</span></label> <input type="text"
							class="form-control" id="street" name="street" placeholder=""
							value="" maxlength="30" required>
						<div class="invalid-feedback">Il nous faut le lieu de retrait (rue) !</div>
					</div>
				</div>

				<div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="zipcode">Code postal <span
							class="text-muted">*</span></label>
                        <input type="number" class="form-control" id="zipcode" name="zipcode" placeholder="" min="01000" max="99999" value="" required>
                        <div class="invalid-feedback">
                            Il nous faut le lieu de retrait (cp) !
                        </div>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label for="city">Ville <span
							class="text-muted">*</span></label>
                        <input type="text" class="form-control" id="city" name="city" placeholder="" maxlength="30" value="" required>
                        <div class="invalid-feedback">
                            Il nous faut le lieu de retrait (ville) !
                        </div>
                    </div>
                </div>
                                
				                
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Ajouter mon article</button>
                <button class="btn btn-primary btn-lg btn-block" type="submit">Annuler</button>
            </form>
        </main>
        <!--footer-->
        <footer class="border-top text-center align-bottom">
            <div class="mt-3">
                <img class="small-icon" src="images/ateni.svg" alt="Eni Ecole">
                <small class="d-block text-muted">&copy; ENI Ecole 2020</small>
                <div>Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
            </div>
        </footer>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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