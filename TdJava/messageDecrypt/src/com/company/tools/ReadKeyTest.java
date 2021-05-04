package com.company.tools;

import com.company.model.Message;
import org.germain.tool.ManaBox;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadKeyTest {
//    @Test
//    public void readKeyTest() {
//        // La clé cryptée donnée plus haut
//        String keyCrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
//        // La clé décryptée que nous devrions obtenir
//        String keyDecrypted = "CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c";
//        // le test d'égalité entre la clé attendue et la sortie de la méthode de la librairie. Si le décryptage ne fonctionne pas nous aurons le message définit ici
//        Assert.assertEquals("La librairie de décryptage est mal installée",keyDecrypted, ManaBox.decrypt(keyCrypted));
//    }
//
//    @Test
//    public void createMapTest() {
//        String testGermain = "6lUjKOzUj4e/Gelw9c6sDLqHniwulClN6XSayZ+HRF/kbZx+CMf95jxrhm4YFSY26OnxVlsrzGkO00IMeAFs3g==";
//        TransCoder transcodeTest = new TransCoder(testGermain);
//        Assert.assertNotNull(transcodeTest.getEncode());
//        System.out.println(transcodeTest.getEncode().toString());
//        Assert.assertNotNull(transcodeTest.getDecode());
//        System.out.println(transcodeTest.getDecode().toString());
//    }
    @Test
    public void decodeTest() {
        String keyCrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        TransCoder transCoderTest = new TransCoder(keyCrypted);
        String messageCoder = "BYAPASBNBGAPASBGASBNASAFBHBGBNAHAJBNAZAFBLADBNAPASASAJAMAPADBNBJBJBJ";
        String messageDecoder = "Les tests sont la pour essayer !!!";
        Assert.assertEquals("le test a échoué", messageDecoder, transCoderTest.decode(messageCoder));
    }

    @Test
    public void encodeTest() {
        String keyCrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        TransCoder transCoderTest = new TransCoder(keyCrypted);
        String messageCoder = "BYAPASBNBGAPASBGASBNASAFBHBGBNAHAJBNAZAFBLADBNAPASASAJAMAPADBNBJBJBJ";
        String messageDecoder = "Les tests sont la pour essayer !!!";
        Assert.assertEquals("le test a échoué", messageCoder, transCoderTest.encode(messageDecoder));
        transCoderTest.getEncode().toString();
    }

    @Test
    public void testMessage(){
        String home = System.getProperty("user.dir");
        Path destinationDuFichierLecture = Paths.get(home,"encoded.txt");
        Path destinationDuFichierEcriture = Paths.get(home,"decoded.txt");
        Path key = Paths.get(home, "key.txt");

        Message message = new Message(true, destinationDuFichierLecture , destinationDuFichierEcriture , key );

    }
}
