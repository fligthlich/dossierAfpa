<?php require 'connexion.php';
$sql = "INSERT INTO disc (`disc_title`, `disc_year`, `disc_picture`, `disc_label`, `disc_genre`, `disc_price`, `artist_id`) VALUES ('Chanson pour les pieds', '2004', 'golman.jpeg', 'france', 'pop', '26.00', '7');";
$request = $bdd->prepare($sql);
var_dump($request->execute());
$request->closeCursor();

?>