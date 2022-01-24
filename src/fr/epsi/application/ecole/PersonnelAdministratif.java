package fr.epsi.application.ecole;

import java.time.LocalDate;

public class PersonnelAdministratif extends Personne{
    private String role;

    public PersonnelAdministratif(String nom, LocalDate dbo, String role) {
        super(nom, dbo);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString(){
        return getNom() + ", ADMINISTRATIF de " + getAge() + " ans, son rôle est " + role;
    }
}
