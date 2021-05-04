<?php
//Trouvez le numéro de semaine de la date suivante : 14/07/2019.
$dateSemaine = new DateTime('14-07-2019');
echo 'La date 14/07/2019 est la semaine ' .$dateSemaine->format('W');
echo "\n";
//Combien reste-t-il de jours avant la fin de votre formation.
$dateDuJour = new DateTime();
$dateFinFormation = new DateTime('28-09-2021');
$diffDate = $dateFinFormation->diff($dateDuJour);
// var_dump($diffDate);
echo "Il me reste {$diffDate->days} avant la fin de formation";
echo "\n";
//Comment déterminer si une année est bissextile ?
$dateBissextile = new DateTime();
$dateBissextile->format("L");
if ($dateBissextile->format("L")) {
    echo "C'est une année bissextile";
}
else{
    echo "Ce n'est pas une année bissextile";
}
echo "\n";
// Montrez que la date du 32/17/2019 est erronée.
// if (checkdate(32,17,2019)) {
//     echo "Date correcte";
// }
// else{
//     echo "Date invalide";
// }
// echo "\n";
$date = DateTime::createFromFormat('j-m-Y','32-17-2019');
var_dump($date->getLastErrors());
$error = $date->getLastErrors(); 
if ($error["error_count"]>0 || $error["warning_count"]>0) {
    echo 'Date invalide';
}
echo "\n";
// Affichez l'heure courante sous cette forme : 11h25.
echo "Il est {$dateDuJour->format("h")} h {$dateDuJour->format("i")}";
echo "\n";
echo "Ou il est {$dateDuJour->format("h:i")}";
echo "\n";
// Ajoutez 1 mois à la date courante.
echo "On est le {$dateDuJour->format("d-m-Y")} si j'ajoute +1 mois on sera le ";
$dateDuJour->modify("+1 month");
echo $dateDuJour->format("d-m-Y");
echo "\n";

// Que s'est-il passé le 1000200000
$dateEnvenement = new DateTime();
$dateEnvenement->setTimestamp(1000200000);
echo $dateEnvenement->format('d-m-Y').' Peux nous rapeller que de mauvais souvenir c\'est le jour des attentats des tours world trade center';
echo "\n";
$d = $dateDuJour->format('d-m-Y');
echo $d;
echo "\n";
setlocale(LC_TIME, 'French');//On redéfini la local en Français echo strftime('%A %d %B %Y',strtotime($dateEnvenement->format('d-m-Y'))); //On affiche la date en français , strtotime transforme le format de la date en timestamp

