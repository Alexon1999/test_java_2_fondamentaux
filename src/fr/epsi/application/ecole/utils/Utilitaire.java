package fr.epsi.application.ecole.utils;

import fr.epsi.application.ecole.comparators.EtudiantMoyenneComparator;
import fr.epsi.application.ecole.comparators.PersonneAgeComparator;
import fr.epsi.application.ecole.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utilitaire {

    /**
     * @param path le chemin vers le fichier
     * @return La Liste de Personne
     * @throws IOException si il y a une erreur dans le path
     */
    public static List<Personne> getPersonsFromFile(String path) throws IOException {
        Path filePath = Path.of(path);
        List<String> lines =  Files.readAllLines(filePath);

        List<Personne> lesPersonnes = new ArrayList<>();

        for (String line : lines){
            String[] lineSplitted = line.split(":");
            String poste = lineSplitted[0];
            String nom = lineSplitted[1];
            LocalDate dbo = LocalDate.parse(lineSplitted[2], DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            if(poste.equals(Poste.ETUDIANT.toString())){
                Double note = Double.parseDouble(lineSplitted[3]);
                Etudiant etudiant = new Etudiant(nom, dbo);
                etudiant.addNote(note);
                lesPersonnes.add(etudiant);
            }else if(poste.equals(Poste.ENSEIGNANT.toString())){
                String matieres = lineSplitted[3];
                Enseignant enseignant = new Enseignant(nom, dbo, matieres);
                lesPersonnes.add(enseignant);
            }else if(poste.equals(Poste.ADMINISTRATIF.toString())){
                String role = lineSplitted[3];
                PersonnelAdministratif personnelAdministratif = new PersonnelAdministratif(nom, dbo, role);
                lesPersonnes.add(personnelAdministratif);
            }
        }

        return lesPersonnes;
    }

    /**
     * Obtenir une Map avec Le nom du poste comme k (clé) et la Liste de Personne comme v (valeur)
     * @param lesPersonnes La liste de personnes à trier selon leurs postes
     * @return Une Map avec Poste (k) et Liste de Personne (v)
     */
    public static Map<Poste, List<Personne>> GetPersonnesParPostes(List<Personne> lesPersonnes){
        Map<Poste, List<Personne>> lesPersonnesParLeursPostes = new HashMap<Poste, List<Personne>>();
        List<Enseignant> lesEnsignants = new ArrayList<>();
        List<Etudiant> lesEtudiants = new ArrayList<>();
        List<PersonnelAdministratif> lesAdministratifs = new ArrayList<>();

        for (Personne p : lesPersonnes){
            // System.out.println(p);
            if(p instanceof Enseignant){
                lesEnsignants.add((Enseignant) p);
            }else if(p instanceof Etudiant){
                lesEtudiants.add((Etudiant) p);
            }else if(p instanceof PersonnelAdministratif){
                lesAdministratifs.add((PersonnelAdministratif) p);
            }
        }

        lesPersonnesParLeursPostes.put(Poste.ENSEIGNANT, lesEnsignants);
        lesPersonnesParLeursPostes.put(Poste.ETUDIANT, lesEtudiants);
        lesPersonnesParLeursPostes.put(Poste.ADMINISTRATIF, lesAdministratifs);

        return lesPersonnesParLeursPostes;
    }

    /**
     * Afficher Le nombre de personne par poste
     * @param personnesParPostes Une Map avec Poste et List de Personnes
     */
    public static void CountViewByJob(Map<Poste, List<Personne>> personnesParPostes){
        for (Poste poste : personnesParPostes.keySet()){
            int size = personnesParPostes.get(poste).size();
            System.out.println("Nous avons " +  size + " " + (size > 1 ? poste + "S" : poste));
        }
    }

    /**
     * Tri par âge
     * @param lesPersonnes La Liste de  Personne à trier par age
     */
    public static void trierPersonnesParAge(List<Personne> lesPersonnes){
        Collections.sort(lesPersonnes, new PersonneAgeComparator());
    }

    /**
     * Tri par Moyenne
     * @param lesEtudiants La Liste de Etudiant à trier selon leur moyenne
     */
    public static void trierEtudiantsParMoyenne(List<Etudiant> lesEtudiants){
        Collections.sort(lesEtudiants, new EtudiantMoyenneComparator());
    }

    /**
     * Obtenir Une Liste avec des étudiants plus de 21 ans
     * @param lesEtudiants La Liste de Etudiants
     * @return Une Liste avec Les Etudiants plus de 21 ans
     */
    public static List<Etudiant> GetListEtudiantsplus21ans(List<Etudiant> lesEtudiants){
        return lesEtudiants.stream().filter(etudiant -> etudiant.getAge() > 21).toList();
    }

    /**
     * Obtenir une Liste Enseignant qui enseigne Anglais
     * @param lesEnseignants
     * @return La Liste de Enseignants Anglais
     */
    public static List<Enseignant> GetListEnseignantsAnglais(List<Enseignant> lesEnseignants){
        return lesEnseignants.stream().filter(enseignant -> enseignant.getMatieres().contains("Anglais")).toList();
    }

    /**
     * Obtenir L'âge moyen des personnes qui travaille dans le RH
     * @param lesAdministrartifs
     * @return l'âge moyen
     */
    public static Integer GetAgeMoyenRH(List<PersonnelAdministratif> lesAdministrartifs){
        return lesAdministrartifs.stream()
                .filter(enseignant -> enseignant.getRole().equals("RH"))
                .reduce(0, (partialAgeResult, enseignant) ->  partialAgeResult + enseignant.getAge(), Integer::sum) / lesAdministrartifs.size();
    }
}
