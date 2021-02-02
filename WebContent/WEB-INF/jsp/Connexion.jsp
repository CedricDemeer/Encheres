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

    <title>ENI-Encheres : Connexion</title>
</head>
<body>
    <div class="container-fluid">
        <!--emptyHeader-->
        <header>
            <nav class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
                <!-- Brand/logo -->
                <a class="navbar-brand" href="index.html">
                    <img class="small-icon" src="images/trocenchere.svg" alt="Accueil ENI-Encheres">
                    <strong>ENI-Encheres</strong>
                </a>
                <a class="navbar-brand" href="#" alt="Gérer mon profil" title="Gérer mon profil">
                    <img class="small-icon" src="images/user.svg">
                    <span class="align-middle text-muted">XXXXX xxx, 0 crédit(s)</span>
                </a>
            </nav>
        </header>

        <!--main bloc-->
        <main>
            <!--title-->
            <div class="mx-auto text-center">
                <h1>Connexion</h1>
                <img class="mb-4 large-icon rounded-circle" src="images/user.svg" alt="">
            </div>
            <!--erreur-->
            <div class="d-flex alert-danger">
                <div class="col-3 p-2">
                    <img class="small-icon" src="images/error.svg">
                </div>
            
                <ul class="col-9 list-unstyled p-2">
                    <li>un message d'erreur éventuellement !</li>
                    <li>un autre message....</li>
                </ul>
            </div>
            <!--formulaire-->
            <form class="form-login" action="#" method="">
                <label for="inputIdentifiant" class="sr-only">Identifiant</label>
                <input type="text" id="inputIdentifiant" class="form-control" name="pseudo" placeholder="Pseudo" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Mot de passe" required>
                <div class="checkbox mb-3">
                    <label>
                    <input type="checkbox" name="remember" value="remember"> Se souvenir de moi
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" title="Me connecter">
                	<img class="small-icon" src="images/connect.svg" alt="Me connecter">
                </button>
                <a href="#">Mot de passe oublié</a>
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
</body>
</html>