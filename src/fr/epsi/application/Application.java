package fr.epsi.application;

import fr.epsi.application.ecole.*;
import fr.epsi.application.ecole.utils.Utilitaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {
        List<Personne> lesPersonnes = Utilitaire.getPersonsFromFile("./resources/personnes.txt");

        Map<Poste, List<Personne>> lesPersonnesParLeursPostes = Utilitaire.GetPersonnesParPostes(lesPersonnes);

        for (Map.Entry<Poste, List<Personne>> entry : lesPersonnesParLeursPostes.entrySet()){
            System.out.println("\n" + entry.getKey() + " : \n");
            for (Personne p : entry.getValue()){
                System.out.println(p);
            }
        }

        Utilitaire.CountViewByJob(lesPersonnesParLeursPostes);
    }
}
