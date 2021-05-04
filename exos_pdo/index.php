<?php 
require 'connexion.php';
$sql = 'SELECT disc_title, artist_name, disc_label, disc_year, disc_genre
        FROM disc
        INNER JOIN artist
        ON disc.artist_id = artist.artist_id;';
$request = $bdd->prepare($sql);
$request->execute();
$lists = $request->fetchAll(PDO::FETCH_ASSOC);
var_dump($lists);
require 'header.php';
?>
    <div class="container">
        <div class="row">
            <div class="col-10">
                <h1>Liste des disques(<?=count($lists)?>)</h1>
            </div>
            <div class="col-2">
                <button class="btn btn-primary m-2" id="ajouter">Ajouter</button>
            </div>
        </div>
        <div class="row">
            <?php foreach ($lists as $key => $value):?>
            <div class="col-md-6 col-12 mt-3 mb-3">
                <div class="row border"><!-- ligne des items -->
                    <div class="col-7">
                        <img class="img" src="img/Dirt.jpeg" alt="photo">
                    </div>
                    <div class="col-5 p-3">
                        <div>
                            <ul class="list-group list-unstyled">
                                <li><strong><?=$value['disc_title']?></strong></li>
                                <li><strong><?=$value['artist_name']?></strong></li>
                                <li><strong>Label: </strong><?=$value['disc_label']?>'</li>
                                <li><strong>Year: </strong><?=$value['disc_year']?></li>
                                <li class="mb-3"><strong>Genre: </strong><?=$value['disc_genre']?></li>
                            </ul>
                        </div>
                        <button class="btn btn-primary mt-3" id="ajouter">Détails</button>
                    </div>
                </div>
            </div>
            <?php endforeach ?>
        </div>
    </div>
<?php require 'footer.php';?>