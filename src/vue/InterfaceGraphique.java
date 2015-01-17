package vue;

import modele.FeuTricolore;

import java.awt.*;

/**
 * Created by Simon on 14/01/2015.
 */
public class InterfaceGraphique extends InterfaceAbstraite {
    private Color couleur;

    public InterfaceGraphique(FeuTricolore feuTricolore) {
        super(feuTricolore);
    }

    @Override
    public void miseAJour() {
        mettreAJourCouleur();
    }

    private void mettreAJourCouleur() {
        FeuTricolore feuTricolore = getSujet();
        if (feuTricolore.estEnMarche()) {
            switch (feuTricolore.getConduite()) {
                case FeuTricolore.PASSEZ:
                    couleur = Color.GREEN;
                    break;
                case FeuTricolore.ATTENTION:
                    couleur = Color.ORANGE;
                    break;
                case FeuTricolore.STOP:
                    couleur = Color.RED;
                    break;
                default:
                    throw new IllegalStateException("Ce mode de conduite n'existe pas.");
            }
        } else if (feuTricolore.estArrete()) {
            couleur = Color.GRAY;
        } else {
            throw new IllegalStateException("Cet etat n'existe pas.");
        }
    }
}
