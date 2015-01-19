package vue;

import modele.FeuTricolore;
import observateur.Observateur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Simon on 14/01/2015.
 */
public abstract class InterfaceAbstraite extends JFrame implements Observateur {
    private FeuTricolore sujet;

    protected JPanel jpBoutons = new JPanel();

    protected JButton jbDemarrer = new JButton("Démarrer");
    protected JButton jbArreter = new JButton("Arreter");
    protected JButton jbChanger = new JButton("Changer");

    public InterfaceAbstraite(String titre, FeuTricolore feuTricolore) {
        super(titre);
        this.setVisible(true);

        jpBoutons.add(jbDemarrer);
        jpBoutons.add(jbArreter);
        jpBoutons.add(jbChanger);

        declarerSujet(feuTricolore);
        attacherActionDemarrer();
        attacherActionArreter();
        attacherActionChanger();
    }

    private void attacherActionDemarrer() {
        jbDemarrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demarrer();
            }
        });
    }

    private void attacherActionArreter() {
        jbArreter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arreter();
            }
        });
    }

    private void attacherActionChanger() {
        jbChanger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changerConduite();
            }
        });
    }

    public void declarerSujet(FeuTricolore feuTricolore) {
        sujet = feuTricolore;
    }

    public abstract void miseAJour();

    private void demarrer() {
        sujet.demarrer();
    }

    private void arreter() {
        sujet.arreter();
    }

    public void changerConduite() {
        sujet.changer();
    };

    public FeuTricolore getSujet() {
        return sujet;
    }
}
