package vue;

import modele.FeuTricolore;
import observateur.Observateur;

/**
 * Created by Simon on 14/01/2015.
 */
public abstract class InterfaceAbstraite implements Observateur {
    private FeuTricolore sujet;

    public InterfaceAbstraite(FeuTricolore feuTricolore) {
        declarerSujet(feuTricolore);
    }

    public void declarerSujet(FeuTricolore feuTricolore) {
        sujet = feuTricolore;
    }

    public abstract void miseAJour();

    public void changerEtat() {
        if (sujet.estEnMarche()) {
            sujet.arreter();
        } else {
            sujet.demarrer();
        }
    };

    public void changerConduite() {
        sujet.changer();
    };

    public FeuTricolore getSujet() {
        return sujet;
    }
}
