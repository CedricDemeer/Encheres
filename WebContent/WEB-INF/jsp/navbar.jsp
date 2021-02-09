<!--
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>-->

<nav class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
                <!-- Brand/logo -->
                <a class="navbar-brand" href="${pageContext.request.contextPath}/Accueil">
                    <img class="small-icon" src="images/trocenchere.svg" alt="Accueil ENI-Encheres">
                    <strong>ENI-Encheres</strong>
                </a>

                <c:if test="${!empty sessionScope.user}">
                <a class="navbar-brand" href="#" alt="Gérer mon profil" title="Gérer mon profil">
                    <img class="small-icon" src="images/user.svg">
                    <span class="align-middle text-muted"></span>                   	
                    
                    ${user.getNom()} ${user.getPrenom()} ${user.getCredit()} crédits
                    
                   
                    
                </a>
				</c:if>
                <ul class="navbar-nav ml-auto">
                    <!-- Dropdown for small screen -->
                    <li class="nav-item dropdown d-lg-none">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            <img class="small-icon" src="images/menu.svg" alt="Menu ENI-Encheres">
                        </a>
                        <div class="dropdown-menu">
                       
                            <a class="dropdown-item" href="#" alt="Administrer le site">Administrer</a> 
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/AjoutArticle" alt="Vendre un article">Vendre un article</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}//Deconnexion" alt="Me déconnecter">Me déconnecter</a>
                           <c:if test="${!empty inscription}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/Inscription" alt="S'inscrire à ENI-Encheres">M'inscrire</a>
                           </c:if>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/Connexion" alt="Se connecter à ENI-Encheres">Me connecter</a>
                        </div>
                    </li>  
                    <!-- Links for medium screen-->
                      	<c:choose> 
                    <c:when test="${sessionScope.user.isAdministrateur()==true}">
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href=./Administrer alt="Administrer le site">Administrer</a>
                    </li> 
                    </c:when>
                    <c:when test="${sessionScope.user.isAdministrateur()==false}">
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href=./AfficherProfil?profil=${user.pseudo}  alt="Gerer Profil">Mon Profil</a>
                    </li> 
                    </c:when>
                 
                    </c:choose>
                   <c:if test="${!empty sessionScope.user}">
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="${pageContext.request.contextPath}/AjoutArticle" alt="Vendre un article">Vendre un article</a>
                    </li>
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Deconnexion" alt="Me déconnecter">Me déconnecter</a>
                    </li>
                    </c:if>
                    <c:when test="${!empty sessionScope.user}">
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Inscription" alt="S'inscrire à ENI-Encheres">M'inscrire</a>
                    </li>
                    </c:when>
                    <c:when test="${!empty sessionScope.user}">
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Connexion" alt="Se connecter à ENI-Encheres">Me connecter</a>
                    </li>
                    </c:when>
                </ul>
            </nav>

