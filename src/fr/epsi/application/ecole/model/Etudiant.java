package fr.epsi.application.ecole.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Etudiant extends Personne {
    private List<Double> notes;

    public Etudiant(String nom, LocalDate dbo) {
        super(nom, dbo);
        notes = new ArrayList<>();
    }

    public void addNote(Double note){
        notes.add(note);
    }

    public Double getMoyenne(){
        Double somme = 0.0;
        for (Double note : notes) {
            somme += note;
        }

        return somme / notes.size();
    }

    @Override
    public String toString(){
        return getNom() + ", ETUDIANT de " + getAge() + " ans, sa moyenne est de " + getMoyenne();
    }
}
