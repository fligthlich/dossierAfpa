<?php
$ligne = 1;
$file = file('http://bienvu.net/misc/customers.csv');
$fields = ['Surname','Firstname','Email','Phone','City','State'];
//pour chaque ligne trouver explode
foreach ($file as $key => $value) {
    $file[$key] = explode(",",$file[$key]);
}
// $file[0]['Nom'] = $file[0][0];
// unset($file[0][0]);

// $file[0][$fields[2]] = $file[0][1];
// unset($file[0][1]);

for ($index = 0; $index < count($file); $index++) { // compte le nombre d'éléments pour file retrour 100 
    for ($i=0; $i < count($file[$index]); $i++) { // compte le nombre d'éléments dans le pour chaque index retourne 6
        $file[$index][$fields[$i]] = $file[$index][$i]; // renome chaque index de l'élement concerner par les éléments du tableau field
        unset($file[$index][$i]); // supprime l'ancien index du tableau 
    }
}
// for ($i=0; $i < count($file[0]); $i++) { 
//     $file[0][$fields[$i]] = $file[0][$i];
//     unset($file[0][$i]);
// }

?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste noms</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <table class="table table-sm table-hover table-striped">
            <thead class="table-dark">
                <tr>
                    <th>N°</th>
                    <?php foreach($fields as $field):?>
                    <th><?=$field?></th>
                    <?php endforeach ?>
                </tr>
            </thead>
            <tbody>
                <?php foreach($file as $key => $value):?>
                <tr>
                    <td><?=$ligne++?></td>
                    <td><?=$value['Surname']?></td>
                    <td><?=$value['Firstname']?></td>
                    <td><?=$value['Email']?></td>
                    <td><?=$value['Phone']?></td>
                    <td><?=$value['City']?></td>
                    <td><?=$value['State']?></td>
                </tr>
                <?php endforeach ?>
            </tbody>
        </table>
    </div>
</body>
</html>



