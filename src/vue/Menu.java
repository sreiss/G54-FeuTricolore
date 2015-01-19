package vue;

import modele.FeuTricolore;
import observateur.Observateur;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by sreiss on 17/01/15.
 */
public class Menu extends JFrame {
    private JPanel jpMenu = new MenuPanel();

    public Menu(String title) {
        super(title);
        this.add(jpMenu);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}

class MenuPanel extends JPanel {
    private HashMap<String, FeuTricolore> carteFeux;

    private JPanel jpBoutons = new JPanel(new FlowLayout());
    private JPanel jpFeux = new JPanel(new FlowLayout());

    private JButton jbAjouterFeu = new JButton("Ajouter un feu");
    private JButton jbInterfaceGraphique = new JButton("Lancer une interface graphique");
    private JButton jbInterfaceTexte = new JButton("Lancer une interface texte");

    private JTable jtFeux;

    public MenuPanel() {
        super(new BorderLayout());

        carteFeux = new HashMap<String, FeuTricolore>();

        jpBoutons.add(jbAjouterFeu);
        jpBoutons.add(jbInterfaceGraphique);
        jpBoutons.add(jbInterfaceTexte);

        Vector<String> nomColonnes = new Vector<String>();
        nomColonnes.add("Nom du feu");
        Vector<Vector> vecteurFeux = new Vector<Vector>();
        jtFeux = new JTable(vecteurFeux, nomColonnes);
        jpFeux.add(new JScrollPane(jtFeux));

        this.add(jpBoutons, BorderLayout.NORTH);
        this.add(jpFeux, BorderLayout.SOUTH);

        attacherActionAjouterFeu();
        attacherActionAjouterInterfaceGraphique();
        attacherActionAjouterInterfaceTexte();
    }

    private void attacherActionAjouterFeu() {
        jbAjouterFeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jopNomFeu = new JOptionPane();
                String nomFeu = jopNomFeu.showInputDialog(MenuPanel.this,
                        "Veuillez entrer le nom du feu :",
                        "Nom du feu",
                        JOptionPane.PLAIN_MESSAGE);

                if (carteFeux.get(nomFeu) == null) {
                    carteFeux.put(nomFeu, new FeuTricolore());
                    Vector<String> newRow = new Vector<String>();
                    newRow.add(nomFeu);
                    DefaultTableModel tableModel = (DefaultTableModel) jtFeux.getModel();
                    tableModel.addRow(newRow);
                } else {
                    jopNomFeu.showMessageDialog(MenuPanel.this,
                            "Ce nom est déjà utilisé, veuillez en choisir un autre.");
                }
            }
        });
    }

    private void attacherActionAjouterInterfaceGraphique() {
        jbInterfaceGraphique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lancerInterface("Interface Graphique", "vue.InterfaceGraphique");
            }
        });
    }

    private void attacherActionAjouterInterfaceTexte() {
        jbInterfaceTexte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lancerInterface("Interface Texte", "vue.InterfaceTexte");
            }
        });
    }

    private void lancerInterface (String nomFenetre, String typeInterface) {
        try {
            int idFeuSelectionne = jtFeux.getSelectedRow();
            String feuSelectionne = (String) jtFeux.getModel().getValueAt(idFeuSelectionne, 0);
            FeuTricolore feu = carteFeux.get(feuSelectionne);

            Class classeInterface = Class.forName(typeInterface);
            Constructor constructor = classeInterface.getConstructor(String.class, FeuTricolore.class);
            Observateur instance = (Observateur) constructor.newInstance(nomFenetre + " - " + feuSelectionne, feu);
            feu.ajouterObservateur(instance);
        } catch (ArrayIndexOutOfBoundsException error) {
            JOptionPane jopPasDeFeux = new JOptionPane();
            jopPasDeFeux.showMessageDialog(MenuPanel.this,
                    "Il n'y a pas de feux pour le moment, ou aucun feu n'a été selectionné !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}