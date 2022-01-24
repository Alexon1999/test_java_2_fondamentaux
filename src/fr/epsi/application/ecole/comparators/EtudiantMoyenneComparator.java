package fr.epsi.application.ecole.comparators;

import fr.epsi.application.ecole.model.Etudiant;

import java.util.Comparator;

public class EtudiantMoyenneComparator implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        return Double.compare(o1.getMoyenne(), o2.getMoyenne());
    }
}
