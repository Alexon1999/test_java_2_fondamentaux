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

    public static Map<Poste, List<Personne>> GetPersonnesParPostes(List<Personne> lesPersonnes){
        Map<Poste, List<Personne>> lesPersonnesParLeursPostes = new HashMap<Poste, List<Personne>>();
        List<Personne> lesEnsignants = new ArrayList<>();
        List<Personne> lesEtudiants = new ArrayList<>();
        List<Personne> lesAdministratifs = new ArrayList<>();

        for (Personne p : lesPersonnes){
            // System.out.println(p);
            if(p instanceof Enseignant){
                lesEnsignants.add(p);
            }else if(p instanceof Etudiant){
                lesEtudiants.add(p);
            }else if(p instanceof PersonnelAdministratif){
                lesAdministratifs.add(p);
            }
        }

        lesPersonnesParLeursPostes.put(Poste.ENSEIGNANT, lesEnsignants);
        lesPersonnesParLeursPostes.put(Poste.ETUDIANT, lesEtudiants);
        lesPersonnesParLeursPostes.put(Poste.ADMINISTRATIF, lesAdministratifs);

        return lesPersonnesParLeursPostes;
    }

    public static void CountViewByJob(Map<Poste, List<Personne>> personnesParPostes){
        for (Poste poste : personnesParPostes.keySet()){
            int size = personnesParPostes.get(poste).size();
            System.out.println("Nous avons " +  size + " " + (size > 1 ? poste + "S" : poste));
        }
    }

    public static void trierPersonnesParAge(List<Personne> lesPersonnes){
        Collections.sort(lesPersonnes, new PersonneAgeComparator());
    }

    public static void trierEtudiantsParMoyenne(List<Etudiant> lesEtudiants){
        Collections.sort(lesEtudiants, new EtudiantMoyenneComparator());
    }

    public static List<Etudiant> GetListEtudiantsplus21ans(List<Etudiant> lesEtudiants){
        return lesEtudiants.stream().filter(etudiant -> etudiant.getAge() > 21).toList();
    }

    public static List<Enseignant> GetListEnseignantsAnglais(List<Enseignant> lesEnseignants){
        return lesEnseignants.stream().filter(enseignant -> enseignant.getMatieres().contains("Anglais")).toList();
    }

    public static Integer GetAgeMoyenRH(List<PersonnelAdministratif> lesAdministrartifs){
        return lesAdministrartifs.stream()
                .filter(enseignant -> enseignant.getRole().equals("RH"))
                .reduce(0, (partialAgeResult, enseignant) ->  partialAgeResult + enseignant.getAge(), Integer::sum) / lesAdministrartifs.size();
    }
}
