<?php 
// Connection à la base de donnée
$bdd = new PDO('mysql:host=localhost;dbname=record','root','root');
$bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$bdd->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_OBJ);


// foreach ($result as $key => $value) {
//     echo $key .' '. $value->disc_title ."\n";
// }