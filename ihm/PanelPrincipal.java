package ihm;

import controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class PanelPrincipal extends JPanel
{
    private Controleur      ctrl;
    private FrameJeu        frame;

    private JPanel          pnlGauche;
    private JPanel          pnlDroit ;
    private JPanel          pnlCentre;

    private JSeparator      separateurPnlGauche;
    private JSeparator      separateurPnlDroit;

    private JTree           arbreTest;
    private JScrollPane     spArbre;

    public PanelPrincipal(Controleur ctrl, FrameJeu frame)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setLayout(new BorderLayout());

        this.pnlGauche  = new JPanel(new BorderLayout());
        this.pnlGauche  .setOpaque(false);
        this.pnlGauche  .setPreferredSize(new java.awt.Dimension(350, 0));

        this.pnlDroit   = new JPanel(new BorderLayout());
        this.pnlDroit   .setOpaque(false);
        this.pnlDroit   .setPreferredSize(new java.awt.Dimension(350, 0));

        this.pnlCentre  = new JPanel(new BorderLayout());
        this.pnlCentre  .setOpaque(false);

        this.separateurPnlGauche = new JSeparator(JSeparator.VERTICAL);
        this.separateurPnlGauche.setOpaque(false);
        this.separateurPnlGauche.setForeground(Color.BLACK);

        this.separateurPnlDroit = new JSeparator(JSeparator.VERTICAL);
        this.separateurPnlDroit.setOpaque(false);
        this.separateurPnlDroit.setForeground(Color.BLACK);

        this.creerArbreTest();

        JPanel pnlContenuGauche = new JPanel(new BorderLayout());
        pnlContenuGauche        .setOpaque(false);

        JLabel lblGauche = new JLabel("Explorateur", SwingConstants.CENTER);
        lblGauche.setFont(new Font("Sans-serif", Font.BOLD, 18));
        lblGauche.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

        pnlContenuGauche.add(lblGauche, BorderLayout.NORTH);
        pnlContenuGauche.add(this.spArbre, BorderLayout.CENTER);

        this.pnlGauche.add(this.separateurPnlGauche, BorderLayout.EAST);

        this.pnlGauche.add(pnlContenuGauche);

        JLabel lblCentre = new JLabel("[Diagramme généré]", SwingConstants.CENTER);
        lblCentre.setFont(new Font("Sans-serif", Font.BOLD, 18));
        this.pnlCentre.add(lblCentre, BorderLayout.CENTER);

        JLabel lblDroit = new JLabel("[Propriétés de la classe]", SwingConstants.CENTER);
        lblDroit.setFont(new Font("Sans-serif", Font.BOLD, 18));
        this.pnlDroit.add(lblDroit, BorderLayout.CENTER);

        this.pnlDroit.add(this.separateurPnlDroit, BorderLayout.WEST);
        this.pnlDroit.setVisible(false);
        
        this.add(pnlGauche, BorderLayout.WEST);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlDroit, BorderLayout.EAST);

        this.setVisible(true);
    }

    public void setVisiblePnlDroit(boolean visible)
    {
        this.pnlDroit.setVisible(visible);
    }

    public void creerArbreTest()
    {
        UIManager.put("Tree.rendererFillBackground", false);
        
        DefaultMutableTreeNode noeudModele = new DefaultMutableTreeNode("Modèle");

        DefaultMutableTreeNode noeudOuvrage = new DefaultMutableTreeNode("Ouvrage");
        DefaultMutableTreeNode noeudAuteur = new DefaultMutableTreeNode("Auteur");
        DefaultMutableTreeNode noeudAdherent = new DefaultMutableTreeNode("Adhérent");
        DefaultMutableTreeNode noeudEmprunt = new DefaultMutableTreeNode("Emprunt");

        noeudModele.add(noeudOuvrage);
        noeudModele.add(noeudAuteur);
        noeudModele.add(noeudAdherent);
        noeudModele.add(noeudEmprunt);

        DefaultMutableTreeNode noeudTitre = new DefaultMutableTreeNode("titre : String");
        DefaultMutableTreeNode noeudAnnee = new DefaultMutableTreeNode("annee : int");
        DefaultMutableTreeNode noeudDispo = new DefaultMutableTreeNode("disponible : boolean");
        DefaultMutableTreeNode noeudGetTitre = new DefaultMutableTreeNode("getTitre() : String");
        DefaultMutableTreeNode noeudEmprunter = new DefaultMutableTreeNode("emprunter() : void");

        noeudOuvrage.add(noeudTitre);
        noeudOuvrage.add(noeudAnnee);
        noeudOuvrage.add(noeudDispo);
        noeudOuvrage.add(noeudGetTitre);
        noeudOuvrage.add(noeudEmprunter);

        DefaultMutableTreeNode noeudNom = new DefaultMutableTreeNode("nom : String");

        noeudAuteur.add(noeudNom);


        DefaultTreeModel arbre  = new DefaultTreeModel(noeudModele);
        this.arbreTest          = new JTree(arbre);
        this.arbreTest          .setOpaque(false);
        this.arbreTest          .setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.arbreTest          .setRootVisible(true);
        this.arbreTest          .setShowsRootHandles(true);
        this.arbreTest          .getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.arbreTest          .expandRow(1);

        ((DefaultTreeCellRenderer) arbreTest.getCellRenderer()).setLeafIcon(null);
        ((DefaultTreeCellRenderer) arbreTest.getCellRenderer()).setOpenIcon(null);
        ((DefaultTreeCellRenderer) arbreTest.getCellRenderer()).setClosedIcon(null);
        ((DefaultTreeCellRenderer) arbreTest.getCellRenderer()).setFont(new Font("Sans-Serif", Font.PLAIN, 18));;

        this.spArbre            = new JScrollPane(this.arbreTest);
        this.spArbre            .setOpaque(false);
        this.spArbre            .getViewport().setOpaque(false);
        this.spArbre            .setBorder(null);


        this.arbreTest.addTreeSelectionListener(new TreeSelectionListener() 
        {
            @Override
            public void valueChanged(TreeSelectionEvent e) 
            {
                if (e.getSource() == arbreTest)
                {
                    DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) arbreTest.getLastSelectedPathComponent();
                    System.out.println("Noeud sélectionné : " + noeud);
                    System.out.println("Chemin complet : " + e.getPath());
                }
            }
        });
    }
}
