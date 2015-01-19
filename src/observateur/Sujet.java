package observateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 15/01/2015.
 */
public abstract class Sujet {
    private List<Observateur> mesObservateurs = new ArrayList<Observateur>();

    public void notifierMesObservateurs() {
        for (Observateur o: mesObservateurs) {
            o.miseAJour();
        }
    }

    public void ajouterObservateur (Observateur o) {
        mesObservateurs.add(o);
    }

    public void supprimerObservateur (Observateur o) {
        mesObservateurs.remove(o);
    }
}
