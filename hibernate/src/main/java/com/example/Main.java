package com.example;

import java.io.File;
import java.util.Collection;


public class Main {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        
        // Crear la carpeta 'data' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        Manager.createSessionFactory();
        
        Ciutat ciu0 = Manager.addCiutat("Tokyo", "Japan", 1000000);
        Ciutat ciu1 = Manager.addCiutat("Shanghai", "China", 200000);
        Ciutat ciu2 = Manager.addCiutat("Seoul", "South Korea", 1000);

        Manager.addCiutada(ciu0.getCiutatId(), "Tatsuki", "Fujimoto", 20);
        Ciutada pep0 = Manager.addCiutada(ciu0.getCiutatId(), "Akiko", "Suzuki", 22);

        Manager.addCiutada(ciu1.getCiutatId(), "Liu", "Cheng", 44);
        Ciutada pep1 = Manager.addCiutada(ciu1.getCiutatId(), "Ji", "Lin", 48);

        Manager.addCiutada(ciu2.getCiutatId(), "Jun", "Lee Park", 62);
        Ciutada pep2 = Manager.addCiutada(ciu2.getCiutatId(), "Kim", "Son", 66);
        
        Collection<?> ciutats = Manager.listCollection(Ciutat.class, "");
        for(Object obj: ciutats) {
            Ciutat cObj = (Ciutat) obj;
            System.out.println("Ciutadans de " + cObj.getNom() + ":");
            Collection<?> ciutadans = Manager.listCollection(Ciutada.class, 
                                            "ciutatId=" + cObj.getCiutatId());
            for(Object obj2: ciutadans) {
            Ciutada cObj2 = (Ciutada) obj2;
            System.out.println("    " + cObj2.toString());
            }
        }

        Manager.delete(Ciutada.class, pep0.getId());
        Manager.delete(Ciutada.class, pep1.getId());
        Manager.delete(Ciutada.class, pep2.getId());
        
        Manager.delete(Ciutat.class, ciu1.getCiutatId());

        System.out.println("Ciutats:");
        Collection<?> llista = Manager.listCollection(Ciutat.class, "");
        for(Object obj: ciutats) {
            Ciutat cObj = (Ciutat) obj;
            System.out.println("  " + cObj);
        }
        System.out.println("Ciutadans:");
        llista = Manager.listCollection(Ciutada.class, "");
        for(Object obj: llista) {
            Ciutada cObj = (Ciutada) obj;
            System.out.println("  " + cObj);
        }
      
        Manager.close();
    }
}