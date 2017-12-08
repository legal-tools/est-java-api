package com.catalyseit;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Est est = new Est("login", "password", "https://secure.esign-tools.fr/api",
                "callbackUrl");
        EstRecipient recip = new EstRecipient("recipMail", "recipFirstname", "recipLastname", 1, 1);
        List<EstRecipient> recipients = new ArrayList<>();
        recipients.add(recip);

        try {
            File f = new File("path");
            byte[] file = Files.readAllBytes(f.toPath());
            //test sendAgreement
            EstResponse response = est.sendAgreement("test", recipients, file, true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
