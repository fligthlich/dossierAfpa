<?php 
function lien( string $link, string $title):string {
    return <<<HTML
    <a href=$link>$title</a>
HTML;
}

echo lien("http://www.google.fr","google");


function somme(array $array) {
    $resultat = 0;
    foreach ($array as $value){
        $resultat += $value;
    }
    return $resultat;
}

$tab = [4, 3, 8, 2];

echo somme($tab);

function complex_password(string $str):bool {
    if ( preg_match('/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/',$str)) {
        return true;
    }
    else{
        return false;
    }
}

$resultat = complex_password('Alexandre1');
echo $resultat;
echo "<br>";






