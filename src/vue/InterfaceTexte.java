package vue;

import modele.FeuTricolore;

/**
 * Created by Simon on 15/01/2015.
 */
public class InterfaceTexte extends InterfaceAbstraite {
    private String ligneEtat;
    private String ligneConduite;

    public InterfaceTexte (FeuTricolore feuTricolore) {
        super(feuTricolore);
    }

    @Override
    public void miseAJour() {
        mettreAJourLigneEtat();
        mettreAJourLigneConduite();
    }

    private void mettreAJourLigneEtat() {
        FeuTricolore feuTricolore = getSujet();
        if (feuTricolore.estEnMarche()) {
            ligneEtat = "en marche";
        } else if (feuTricolore.estArrete()) {
            ligneEtat = "en stand-by";
        } else {
            throw new IllegalStateException("Cet etat n'existe pas.");
        }
    }

    private void mettreAJourLigneConduite() {
        FeuTricolore feuTricolore = getSujet();
        switch (feuTricolore.getConduite()) {
            case FeuTricolore.PASSEZ:
                ligneConduite = "passez";
                break;
            case FeuTricolore.ATTENTION:
                ligneConduite = "attention";
                break;
            case FeuTricolore.STOP:
                ligneConduite = "stop";
                break;
            default:
                throw new IllegalStateException("Ce mode de conduite n'existe pas.");
        }
    }
}
