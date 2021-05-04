<?php 

if ($_POST) {
    if (isset($_POST['nom']) && !empty($_POST['nom']) && preg_match("/^[a-zA-Z]$/")){
        echo "tous est ok";
    }
    else{
        echo "champs non rempli";
    }
}









?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <form action="formulaire.php"  method="POST">
                    <div class="form-group">
                        <label class="form-label" for="nom">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom">
                    </div>
                    <div class="form-group">
                        <label for="email" class="form-label">Email</label>
                        <input class="form-control" type="email" name="email" id="email">
                    </div>
                    <div class="form-group">
                        <label for="Sujet" class="form-label">Sujet</label>
                        <textarea class="form-control" name="sujet" id="Sujet" cols="30" rows="4" placeholder="Votre sujet"></textarea>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary" type="reset" value="Annuler">
                        <input class="btn btn-primary" type="submit" value="Valider">
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>