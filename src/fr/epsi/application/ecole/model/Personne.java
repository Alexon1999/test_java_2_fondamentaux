package fr.epsi.application.ecole.model;

import java.time.LocalDate;

public class Personne {
    private String nom;
    private LocalDate dbo;

    public Personne(String nom, LocalDate dbo) {
        this.nom = nom;
        this.dbo = dbo;
    }

    public String getNom() {
        return nom;
    }

    public Integer getAge(){
        return LocalDate.now().getYear() -  dbo.getYear();
    }
}
