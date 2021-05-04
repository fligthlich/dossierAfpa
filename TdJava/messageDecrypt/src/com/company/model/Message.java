package com.company.model;

import com.company.tools.TransCoder;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private boolean encoded;
    private List<String> msgClear = new ArrayList<String>();
    private List<String> msgEncoded = new ArrayList<String>();
    private Path msgClearPath ;
    private Path msgEncodedPath;
    private Path keyPath;
    private String key;
    private TransCoder transcoder;

    /**
     * Constructeur de la classe message
     * @param encoded
     * @param msgClearPath
     * @param msgEncodedPath
     * @param keyPath
     */
    public Message(boolean encoded, Path msgClearPath, Path msgEncodedPath, Path keyPath) {
        this.encoded = encoded;
        this.msgClearPath = msgClearPath;
        this.msgEncodedPath = msgEncodedPath;
        this.keyPath = keyPath;
        this.readNwrite();
    }

    public void readNwrite(){
        //Si le message est encoder on le décode et on écrit dans le fichier passer dans le constructeur msgClearPath
        if (this.encoded){
            //On capture l'exception si il y en a une
            try {
                // Récupération du contenu de chaque lignes du fichier dans le fichier (chemin du fichier msgEncodedPath)
                this.msgEncoded = Files.readAllLines(this.msgEncodedPath);
                // Récupération de la clé dans le fichier
                this.key = Files.readString(this.keyPath);
            }catch (IOException e){
                e.getMessage();
            }
            // Création d'un nouveau transcoder
            this.transcoder = new TransCoder(key);
            //Boucle qui permet de lire chaque ligne du fichier encoder contenu dans la variable msgEncoded et le decode puis on l'ajoute dans la liste msgClear
            for (String line : this.msgEncoded){
                transcoder.decode(line);
                this.msgClear.add(this.transcoder.decode(line));
            }
            //Boucle qui permet d'écrire dans le fichier ligne par ligne
            for (String line : this.msgClear){
                try{
                    Files.writeString(this.msgClearPath, line + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }catch (IOException e){
                    e.getMessage();
                }
            }
        // Sinon si le fichier est en clair on l'encode et on l'écrit dans le fichier msgEncodedPath
        }else{
            //On capture l'exception si il y en a une
            try {
                // Récupération du contenu de chaque lignes du fichier dans la liste msgEncode
                this.msgClear = Files.readAllLines(this.msgClearPath);
                System.out.println("****" + msgClear + "*****");
                // Récupération de la clé dans le fichier
                key = Files.readString(this.keyPath);
            }catch (IOException e){
                e.getMessage();
            }
            // Création d'un nouveau transcoder
            this.transcoder = new TransCoder(key);
            //Boucle qui permet de lire chaque ligne du fichier encoder contenu dans la variable msgEncoded et le decode puis on l'ajoute dans la liste msgClear
            for (String line : this.msgClear){
                this.msgEncoded.add(this.transcoder.encode(StringUtils.stripAccents(line)));
            }
            System.out.println(msgEncoded);
            //Boucle qui permet d'écrire dans le fichier ligne par ligne
            for (String line : this.msgEncoded){
                try{
                    Files.writeString(this.msgEncodedPath, line + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }catch (IOException e){
                    e.getMessage();
                }
            }
        }
    }
}

