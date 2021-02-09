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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
    <!-- Personnal CSS-->
    <link rel="stylesheet" href="css/style.css">

    <!--icons-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <title>ENI-Encheres : Afficher profil</title>
</head>
<body>
    <div class="container-fluid">
        <!--fullHeader-->
        <header>
            <%@ include file="navbar.jsp"%>
        </header>
        <!--main bloc-->
        <main>
            <!--title
            <div class="mx-auto text-center">
                <h1>Profil</h1>
            </div>-->

            <!--Profil sous format carte-->
            <div class="row justify-content-center border-top card-deck">
                <div class="col-12 col-sm-6 p-2" >
                    <div class="card">
                     <div class="card-header text-center">
                            <h4 class="my-0 font-weight-normal">Profil</h4>
                        </div>

						<div class="container d-flex justify-content-center mt-3">
							<div class="pr-5">
								<p>Pseudo :</p>
								<p>Nom :</p>
								<p>Prénom :</p>
								<p>Email :</p>
								<p>Téléphone :</p>
								<p>Rue :</p>
								<p>Code Postal :</p>
								<p>Ville :</p>
							</div>
							<div class="text-center pl-5">
								<p>${utilisateur.pseudo }</p>
								<p>${utilisateur.nom }</p>
								<p>${utilisateur.prenom }</p>
								<p>${utilisateur.email }</p>
								<c:if test="${empty utilisateur.telephone}">
								<p>Pas de numéro de téléphone</p>
								</c:if>
								<p>${utilisateur.telephone }</p>
								<p>${utilisateur.rue }</p>
								<p>${utilisateur.codePostal }</p>
								<p>${utilisateur.ville }</p>
							</div>
						</div>
						<c:if test="${sessionScope.user.noUtilisateur == utilisateur.noUtilisateur }">
                        <a class="mt-3 btn btn-lg btn-block btn-primary" href="${pageContext.request.contextPath}/ModifierProfil" title="Modifier">
                            Modifier
                        </a>
                        
                        </c:if>
                    </div>
                </div>
                
               
	        </div>
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