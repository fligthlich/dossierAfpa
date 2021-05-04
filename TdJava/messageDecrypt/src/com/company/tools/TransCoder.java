package com.company.tools;

import org.germain.tool.ManaBox;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TransCoder {
    /**
     * Propriété encode
     * Type Map<Charactere,String>
     */
    private Map<Character, String> encode = new LinkedHashMap<Character,String>();

    /**
     * Propriété decode
     * Type Map<String, Charactere>
     */
    private Map<String, Character> decode = new LinkedHashMap<String, Character>();

    /**
     * Constructeur de la class Transcoder qui reçoit en paramêtre une clé
     * qui est décrypter affin de pouvoir obtenir une map à partir de celle ci
     * @param cleCrypter
     */
    public TransCoder(String cleCrypter) {
        String cle = ManaBox.decrypt(cleCrypter);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int a = 0;
        int b = 0;
        String code = "AA";
        for (int i = 0; i < cle.length(); i++){
            if (code.substring(1,2).equals("Z")){
                a++;
                b = 0;

            }
            code = alpha.substring(a,a+1) + alpha.substring(b,b+1);
            this.encode.put(cle.charAt(i), code);
            this.decode.put(code, cle.charAt(i));
            b++;
        }

    }

    /**
     * Methode qui permet d'afficher la propriété encode (affiche la map)
     * @return
     */
    public Map<Character, String> getEncode() {

        return encode;
    }

    /**
     * Methode qui permet d'afficher la propriété decode (affiche la Map)
     * @return
     */
    public Map<String, Character> getDecode() {
        return decode;
    }

    /**
     * Methode qui permet d'encoder le message
     * @param msg
     * @return
     */
    public String encode(String msg){
        String messageEncode = "";
        for (char letter : msg.toCharArray()){
            messageEncode = messageEncode + this.encode.get(letter);
        }
         //System.out.println(messageEncode);
        return messageEncode;
    }

    /**
     * methode qui permet de décoder le message
     * @param msg
     * @return
     */
    public String decode(String msg){
        String messageDecode = "";
        for (int i = 0; i < msg.length(); i+= 2){
            messageDecode = messageDecode + this.decode.get(msg.substring(i,i+2));
        }
        //System.out.println(messageDecode);
        return messageDecode;
    }
}
