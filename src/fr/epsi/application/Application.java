package fr.epsi.application;

import fr.epsi.application.ecole.model.Personne;
import fr.epsi.application.ecole.model.Poste;
import fr.epsi.application.ecole.utils.Utilitaire;

import java.io.IOException;
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

        Utilitaire.trierPersonnesParAge(lesPersonnes);
        //Utilitaire.trierEtudiantsParMoyenne(lesPersonnesParLeursPostes.get(Poste.ETUDIANT));

    }
}
