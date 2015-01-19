package modele;

import observateur.Sujet;

/**
 * Created by Simon on 14/01/2015.
 */
public class FeuTricolore extends Sujet {
    public static final boolean EN_STAND_BY = false;
    public static final boolean EN_MARCHE = true;

    public static final int PASSEZ = 0;
    public static final int ATTENTION = 1;
    public static final int STOP = 2;

    private boolean etat;
    private int conduite;

    public FeuTricolore() {
        etat = EN_STAND_BY;
        conduite = STOP;
    }

    public void arreter() {
        if (estEnMarche()) {
            etat = EN_STAND_BY;
            conduite = STOP;
        }
        notifierMesObservateurs();
    }

    public void demarrer() {
        if (estArrete()) {
            etat = EN_MARCHE;
            conduite = STOP;
        }
        notifierMesObservateurs();
    }

    public void changer() {
        if (estEnMarche()) {
            switch (conduite) {
                case PASSEZ:
                    conduite = ATTENTION;
                    break;
                case ATTENTION:
                    conduite = STOP;
                    break;
                default:
                    conduite = PASSEZ;
                    break;
            }
            notifierMesObservateurs();
        }
    }

    public boolean estArrete() {
        return etat == EN_STAND_BY;
    }

    public boolean estEnMarche() {
        return etat == EN_MARCHE;
    }

    public int getConduite() {
        return conduite;
    }
}
