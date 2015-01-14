package modele;

/**
 * Created by Simon on 14/01/2015.
 */
public class FeuTricolore {
    private static final boolean EN_STAND_BY = false;
    private static final boolean EN_MARCHE = true;

    private static final int PASSEZ = 0;
    private static final int ATTENTION = 1;
    private static final int STOP = 2;

    private boolean etat;
    private int conduite;

    public void arreter() {
        if (estEnMarche()) {
            etat = EN_STAND_BY;
            conduite = 2;
        }
    }

    public void demarrer() {
        if (estArrete()) {
            etat = EN_MARCHE;
        }
    }

    public void changer() {
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
    }

    public boolean estArrete() {
        return etat == EN_STAND_BY;
    }

    public boolean estEnMarche() {
        return etat == EN_MARCHE;
    }
}
