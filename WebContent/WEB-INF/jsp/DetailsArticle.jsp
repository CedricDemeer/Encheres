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
<title>ENI-Encheres : Détails de l'article</title>
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
			<div class="row justify-content-center mt-3">
				<img class="mb-4 large-icon" src="images/article.svg" alt="">
				<h1>Détails de l'article</h1>

			</div>
			<div class="container">
				<div class="row">
					<div class="col">
						<c:if test="${!empty article.image}">
							<img class="img-fluid img-thumbnail"
								src="imagesupload/${article.image}" alt="">
						</c:if>
						<c:if test="${empty article.image}">
							<img class="mb-4 large-icon" src="images/article.svg" alt="">
						</c:if>

					</div>


					<!--Détail de l'article-->

					<div class="col-6">

						<div class="container d-flex justify-content-center mt-3">
							<div class="pr-5">
								<h2>${article.nomArticle}</h2>
								<h6>Description :</h6>
								<c:if test="${empty article.description}">
									<p>Voici une description fictive de l'article</p>
								</c:if>
								<p>${article.description}</p>
								
								<h6>Catégorie :</h6>
								<c:if test="${empty article.categorie.libelle}">
									<p>Voici la catégorie</p>
								</c:if>
								<p>${article.categorie.libelle}</p>
								
								<h6>Meilleur offre :</h6>
								<c:if test="${empty article.listeEncheres}">
									<p>Meilleur enchère</p>
								</c:if>
								<p>${article.enchere}</p>
								
								<h6>Mise à prix :</h6>
								<c:if test="${empty article.miseAPrix}">
									<p>Montant initial de l'article</p>
								</c:if>
								<p>${article.miseAPrix}</p>
								
								<h6>Date de fin d'enchère :</h6>
								<p>${article.dateFinEncheres}</p>
								
								<h6>Retrait :</h6>
								<c:if test="${empty article.lieuRetrait.rue}">
									<p>pas de rue</p>
								</c:if>
								
								<c:if test="${empty article.lieuRetrait.code_postal}">
									<p>pas de CP</p>
								</c:if>
								<c:if test="${empty article.lieuRetrait.ville}">
									<p>pas de ville</p>
								</c:if>
								<p>${article.lieuRetrait.rue}</p>
								<p>${article.lieuRetrait.code_postal}</p>
								<p>${article.lieuRetrait.ville}</p>
								
								<h6>Vendeur :</h6>
								<c:if test="${empty article.utilisateur.pseudo}">
									<p>Le vendeur</p>
								</c:if>
								<p>${article.utilisateur.pseudo }</p>
								
							</div>
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-4">
						<!--Formulaire pour enchérir-->
						<form class="form-register needs-validation" novalidate
							method="post" action="">
							<c:if test="${article.etatVente =='EC' }">
							<h6>
								<label for="monEnchere">Faire une proposition :</label>
							</h6>
							<input type="number" class="form-control" id="encherir"
								name="encherir" placeholder="${article.enchere}">
							<button class="btn btn-primary btn-lg btn-block" type="submit">Faire
								une offre</button>
							</c:if>
							
						<c:if test="${article.etatVente == 'CR' } ">
							<p>Article bientôt en vente !<p>
						</c:if>
						
						</form>
						<c:if test="${sessionScope.user.noUtilisateur == article.utilisateur.noUtilisateur && article.etatVente == 'CR' }">
						<a class="mt-3 btn btn-lg btn-block btn-warning" href="${pageContext.request.contextPath}/ModifierArticle?article=${article.noArticle}" title="Modifier">
                            Modifier
                        </a>
						</c:if>
					</div>
				</div>
			</div>
		</main>
	</div>
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
            	checkAchats();
            	checkVentes();
                achats.addEventListener('change', function(event) {
                	checkAchats();
                }, false);
                ventes.addEventListener('change', function(event) {
                	checkVentes();
                }, false);
                
                function checkAchats() {
                	//id radio button achats
                	var achats = document.getElementById('achats');
                    if (achats.checked){
                    	//id des checkbox
                        document.getElementById('venteencours').disabled = true;
                        document.getElementById('nondebutees').disabled = true;
                        document.getElementById('terminees').disabled = true;
                        document.getElementById('encours').disabled = false;
                        document.getElementById('ouvertes').disabled = false;
                        document.getElementById('remportees').disabled = false;
                    }
                }
                function checkVentes(){
                	//id radio button ventes
                	var ventes = document.getElementById('ventes');
                    if (ventes.checked){
                    	//id des checkbox
                        document.getElementById('venteencours').disabled = false;
                        document.getElementById('nondebutees').disabled = false;
                        document.getElementById('terminees').disabled = false;
                        document.getElementById('encours').disabled = true;
                        document.getElementById('ouvertes').disabled = true;
                        document.getElementById('remportees').disabled = true;
                    }
                }
            }, false);
        })();
    </script>
</body>
</html>