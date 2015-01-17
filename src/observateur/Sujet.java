package observateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 15/01/2015.
 */
public abstract class Sujet {
    private List<Observateur> mesObservateurs = new ArrayList<Observateur>();

    public void notifierMesObservateurs() {

    }

    public void ajouterObservateur (Observateur o) {

    }

    public void supprimerObservateur (Observateur o) {

    }
}
