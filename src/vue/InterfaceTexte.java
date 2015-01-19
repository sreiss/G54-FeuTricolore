package vue;

import modele.FeuTricolore;
import observateur.Observateur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Simon on 15/01/2015.
 */
public class InterfaceTexte extends InterfaceAbstraite {
    private String ligneEtat;
    private String ligneConduite;

    private JPanel jpInterfaceTexte = new JPanel(new BorderLayout());
    private JPanel jpLignes = new JPanel();

    private JTextField jtfLigneEtat = new JTextField();
    private JTextField jtfLigneConduite = new JTextField();

    public InterfaceTexte (String titre, FeuTricolore feuTricolore) {
        super(titre, feuTricolore);

        jtfLigneConduite.setEnabled(false);
        jtfLigneEtat.setEnabled(false);
        jpLignes.add(jtfLigneEtat);
        jpLignes.add(jtfLigneConduite);
        miseAJour();
        jpLignes.setLayout(new BoxLayout(jpLignes, BoxLayout.Y_AXIS));

        jpInterfaceTexte.add(jpLignes, BorderLayout.NORTH);
        jpInterfaceTexte.add(jpBoutons, BorderLayout.SOUTH);

        this.add(jpInterfaceTexte);
        super.pack();
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
        jtfLigneEtat.setText(ligneEtat);
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
        jtfLigneConduite.setText(ligneConduite);
    }
}
