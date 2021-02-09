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
						<img class="mb-4 large-icon" src="${article.image}" alt="">
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
								<h5>Description :</h5>
								<c:if test="${empty article.description}">
									<p>Voici une description fictive de l'article</p>
								</c:if>
								<p>${article.description}</p>
								<h5>Catégorie :</h5>
								<c:if test="${empty article.categorie}">
									<p>Voici la catégorie</p>
								</c:if>
								<p>${article.categorie}</p>
								<h5>Meilleur offre :</h5>
								<c:if test="${empty article.listeEncheres}">
									<p>Meilleur enchère</p>
								</c:if>
								<p>${article.listeEncheres}</p>
								<h5>Mise à prix :</h5>
								<c:if test="${empty article.miseAPrix}">
									<p>Montant initial de l'article</p>
								</c:if>
								<p>${article.miseAPrix }</p>
								<h5>Retrait :</h5>
								<c:if test="${empty article.lieuRetrait}">
									<p>Montant initial de l'article</p>
								</c:if>
								<p>${article.lieuRetrait }</p>
								<h5>Vendeur :</h5>
								<c:if test="${empty article.utilisateur}">
									<p>Le vendeur</p>
								</c:if>
								<p>${article.utilisateur }</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-4">
						<!--Formulaire pour enchérir-->
						<form class="form-register needs-validation" novalidate
							method="post" action="">
							<h5>
								<label for="monEnchere">Faire une proposition :</label>
							</h5>
							<input type="number" class="form-control" id="encherir"
								name="encherir" placeholder="¤${article.enchere}">
							<button class="btn btn-primary btn-lg btn-block" type="submit">Faire
								une offre</button>
						</form>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>