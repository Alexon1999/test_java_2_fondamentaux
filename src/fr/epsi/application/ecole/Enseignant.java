package fr.epsi.application.ecole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enseignant extends Personne{
    private String matieres;

    public Enseignant(String nom, LocalDate dbo, String matieres) {
        super(nom, dbo);
        this.matieres = matieres;
    }

    public String getMatieres() {
        return matieres;
    }

    @Override
    public String toString(){
        return getNom() + ", ENSEIGNANT de " + getAge() + " ans, les matières qu'il ensigne sont " + matieres;
    }
}
