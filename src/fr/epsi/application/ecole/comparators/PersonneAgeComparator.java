package fr.epsi.application.ecole.comparators;

import fr.epsi.application.ecole.Personne;

import java.util.Comparator;

public class PersonneAgeComparator implements Comparator<Personne> {

    @Override
    public int compare(Personne o1, Personne o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
