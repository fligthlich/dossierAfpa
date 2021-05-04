package com.company.GUI;

import com.company.model.Message;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu {
    private String home = System.clearProperty("user.dir");
    private String nameFileEncoded;
    private String nameFileDecode;
    private Path msgEncodedPath;
    private String nameFileKey;
    private Path keyPath;
    private Path msgClearPath;

    public Menu() {
        System.out.println(
                        "╔══════════════════════════════════════╗\n" +
                        "║   System d'encodage et de décodage   ║\n" +
                        "║             de messages              ║\n" +
                        "╠══════════════════════════════════════╣\n" +
                        "║       1) Décoder un message          ║\n" +
                        "║       2) Encode un message           ║\n" +
                        "║       3) Quitter                     ║\n" +
                        "╚══════════════════════════════════════╝");
        int choice = Integer.parseInt(input("Votre choix ?"));

        switch (choice){
            case 1:
                choiceDecode();
                break;
            case 2:
                choiceEncode();
                break;
            case 3:
                System.out.println("Merci d'avoir utiliser le logiciel de cryptage et décryptage");
                System.out.println("********FIN DU PROGRAMME*********");
                break;
            default:
                System.out.println("Choix incorect recommencer !!!!");
        }

    }

    private String input(String messageConsole){
        Scanner sc = new Scanner(System.in);
        System.out.println(messageConsole);
        System.out.print("-> ");
        String choice = sc.nextLine();
        return choice;
    }

    private void choiceDecode(){
        this.nameFileDecode = input("Entrer le nom du fichier à décoder");
        this.msgEncodedPath = Paths.get(this.home, this.nameFileDecode + ".txt");
        if (!Files.exists(this.msgEncodedPath)){
            System.out.println("Le fichier n'existe pas");
            return;
        }
        this.nameFileKey = input("Entrer le nom du fichier contenant la clé");
        this.keyPath = Paths.get(this.home, this.nameFileKey + ".txt");
        if (!Files.exists(this.keyPath)){
            System.out.println("Le fichier n'existe pas !!!");
            return;
        }
        this.msgClearPath = Paths.get(home, "decode.txt");
        Message message = new Message(true,this.msgClearPath,this.msgEncodedPath,this.keyPath);
        System.out.println(
                "=======Décodage=======\n"+
                        "pour voir si ça marche\n"+
                        "Le message décodé se trouve: " + this.msgClearPath);
        return;
    }

    private void choiceEncode(){
        this.nameFileEncoded = input("Entrer le nom du fichier à encoder");
        this.msgClearPath = Paths.get(this.home, this.nameFileEncoded + ".txt");
        if (!Files.exists(this.msgClearPath)){
            System.out.println("Le fichier n'existe pas");
            return;
        }
        this.nameFileKey = input("Entrer le nom du fichier contenant la clé");
        this.keyPath = Paths.get(this.home, this.nameFileKey + ".txt");
        if (!Files.exists(this.keyPath)){
            System.out.println("Le fichier n'existe pas !!!");
            return;
        }
        this.msgEncodedPath = Paths.get(home, "messageEncoder.txt");
        Message message = new Message(false,this.msgClearPath,this.msgEncodedPath,this.keyPath);
        System.out.println(
                "========Encodage=======\n"+
                        "pour voir si ça marche\n"+
                        "Le message encoder se trouve: " + this.msgEncodedPath);
        return;
    }

}
