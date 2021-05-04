<?php 
$title = "Nouveau produit";
require 'header.php';?>
    <div class="container">
        <h1>Ajouter un vinyle</h1>
        <form action="test.php" method="POST">
            <div class="form-group">
                <label for="title">Title</label>
                <input class="form-control" type="text" name="title" id="title" placeholder="Enter title">
            </div>
            <div class="form-group">
                <label for="artist">Artist</label>
                <select class="form-control" id="artist">
                    <option>Queens of the song</option>
                </select>
            </div>
            <div class="form-group">
                <label for="year">Year</label>
                <input class="form-control" type="number" name="year" id="year" placeholder="Enter year">
            </div>
            <div class="form-group">
                <label for="genre">Genre</label>
                <input class="form-control" type="text" name="genre" id="genre" placeholder="Enter genre">
            </div>
            <div class="form-group">
                <label for="label">Label</label>
                <input class="form-control" type="text" name="label" id="label" placeholder="Enter label">
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input class="form-control" type="text" name="price" id="price" placeholder="Enter price">
            </div>
            <div class="form-group">
                <label for="file">Picture</label>
                <input type="file" class="form-control-file" id="file">
            </div>
            <div>
                <!-- <button class="btn btn-primary" type="submit">Ajouter</button> -->
                <!-- <button class="btn btn-primary">Retour</button> -->
                <input type="submit" value="Ajouter">
            </div>
        </form>
    </div>
<?php require 'footer.php';?>