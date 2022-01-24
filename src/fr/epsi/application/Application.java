package fr.epsi.application;

import fr.epsi.application.ecole.model.*;
import fr.epsi.application.ecole.utils.Utilitaire;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {
        List<Personne> lesPersonnes = Utilitaire.getPersonsFromFile("./resources/personnes.txt");

        Map<Poste, List<? extends Personne>> lesPersonnesParLeursPostes = Utilitaire.GetPersonnesParPostes(lesPersonnes);

        for (Map.Entry<Poste, List<? extends Personne>> entry : lesPersonnesParLeursPostes.entrySet()){
            System.out.println("\n" + entry.getKey() + " : \n");
            for (Personne p : entry.getValue()){
                System.out.println(p);
            }
        }
        System.out.println("\n");

        Utilitaire.CountViewByJob(lesPersonnesParLeursPostes);

        Utilitaire.trierPersonnesParAge(lesPersonnes);
        Utilitaire.trierEtudiantsParMoyenne((List<Etudiant>) lesPersonnesParLeursPostes.get(Poste.ETUDIANT));

        System.out.println("\nLes Etudiants plus de 21 ans : ");
        for(Etudiant e: Utilitaire.GetListEtudiantsplus21ans((List<Etudiant>) lesPersonnesParLeursPostes.get(Poste.ETUDIANT))){
            System.out.println(e.getNom());
        }

        System.out.println("\nNos Profs d'Anglais : ");
        for(Enseignant e: Utilitaire.GetListEnseignantsAnglais((List<Enseignant>) lesPersonnesParLeursPostes.get(Poste.ENSEIGNANT))){
            System.out.println(e.getNom());
        }

        System.out.println("\nL'Âge Moyen du personnel travaillant dans le RH est de " + Utilitaire.GetAgeMoyenRH((List<PersonnelAdministratif>) lesPersonnesParLeursPostes.get(Poste.ADMINISTRATIF)) + " ans");

        System.out.println("\nGenerating Files .....");
        Utilitaire.GenerateFilesForEachJob(lesPersonnesParLeursPostes, "./resources");
    }
}
