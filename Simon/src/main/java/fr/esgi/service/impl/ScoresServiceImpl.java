package fr.esgi.service.impl;

import java.util.List;

public class ScoresServiceImpl {

    /**
     * Compare deux listes d'entiers.
     *
     * @param liste1 La première liste d'entiers
     * @param liste2 La deuxième liste d'entiers
     * @return -1 si les listes n'ont pas la même taille ou ne sont pas similaires, sinon 2 si elles sont similaires
     */
    public int comparerListes(List<Integer> liste1, List<Integer> liste2) {
        if (liste1.size() != liste2.size()) {
            return -2;
        }

        for (int i = 0; i < liste1.size(); i++) {
            if (!liste1.get(i).equals(liste2.get(i))) {
                return -2;
            }
        }

        return 2;
    }
}