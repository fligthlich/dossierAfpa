<?php 
// var_dump(__DIR__); Affiche le repertoire courant dans lequel se trouve le script d'execution
// $fichier = __DIR__ .DIRECTORY_SEPARATOR. 'liens2.txt';
// file_put_contents($fichier,"http://www.python.org/
// https://fr.wikipedia.org/wiki/Joel_et_Ethan_Coen
// http://fr.wikipedia.org/wiki/Sp%C4%B1n%CC%88al_Tap\
// https://www.theclash.com/landing/
// http://theforensics.net/");

// var_dump(dirname(__DIR__, 4)); renvoi le chemin du dossier parent recule de 4 dossier en arrière 
$file = file('https://ncode.amorce.org/ressources/Pool/CDA/WEB_PHP_frc/liens.txt');
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>fichier</title>
</head>
<body>
    <ul>
    <?php
    foreach ($file as $key => $value) :?>
        <li><a href="<?=$value?>"><?=$value?></a></li>
    <?php endforeach ?>
    </ul>
</body>
</html>





