package ihm;

import controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameJeu extends JFrame implements ActionListener
{
    private Controleur          ctrl;
    private PanelPrincipal      pnlActif;

    private JMenuItem           itemOuvrir;
    private JMenuItem           itemEnregister;
    private JMenuItem           itemEnregistrerSous;
    private JMenuItem           itemExporter;
    private JCheckBoxMenuItem   itemAfficherInspecteur;

    private JToggleButton       tgInspecteur;

    public FrameJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.pnlActif = new PanelPrincipal(ctrl, this);

        this.setTitle("Générateur de diagrammes de classe à partir d'un fichier XML");
        this.setSize(800,800);
        this.setLayout(new BorderLayout());

        this.creerMenuBar();

        this.add(pnlActif, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    // Permet de créer la barre de menu et ses sous-menus
    private void creerMenuBar()
    {
        // Création de la barre de menu principale
        JMenuBar barreMenu          = new JMenuBar();
        barreMenu                   .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JMenu menuFichier           = new JMenu("Fichier");
        menuFichier                 .setMnemonic(KeyEvent.VK_F);
        menuFichier                 .setFont(new Font("Sans-Serif", Font.BOLD, 18));
        menuFichier                 .setForeground(Color.BLACK);
        menuFichier                 .setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.itemOuvrir             = new JMenuItem("Ouvrir un fichier XML...");
        this.itemOuvrir             .setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.itemOuvrir             .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));  // pour CTRL+O
        this.itemOuvrir             .addActionListener(this);

        this.itemEnregister          = new JMenuItem("Enregistrer");
        this.itemEnregister          .setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.itemEnregister          .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));  // pour CTRL+S
        this.itemEnregister          .addActionListener(this);

        this.itemEnregistrerSous     = new JMenuItem("Enregistrer sous...");
        this.itemEnregistrerSous     .setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.itemEnregistrerSous     .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));  // pour CTRL+SHIFT+S
        this.itemEnregistrerSous     .addActionListener(this);

        this.itemExporter           = new JMenuItem("Exporter en PDF... >");
        this.itemExporter           .setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.itemExporter           .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));  // pour CTRL+P
        this.itemExporter           .addActionListener(this);

        JMenu menuEdition           = new JMenu("Édition");
        menuEdition                 .setFont(new Font("Sans-Serif", Font.BOLD, 18));
        menuEdition                 .setMnemonic(KeyEvent.VK_E);
        menuEdition                 .setForeground(Color.BLACK);
        menuEdition                 .setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.itemAfficherInspecteur = new JCheckBoxMenuItem("Afficher l'inspecteur");
        this.itemAfficherInspecteur.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.itemAfficherInspecteur.addActionListener(this);

        JMenu menuAffichage         = new JMenu("Affichage");
        menuAffichage               .setMnemonic(KeyEvent.VK_A);
        //menuAffichage           .setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK                              ));  // pour CTRL+A
        menuAffichage               .setFont(new Font("Sans-Serif", Font.BOLD, 18));
        menuAffichage               .setForeground(Color.BLACK);
        menuAffichage               .setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        this.tgInspecteur           = new JToggleButton("≡");
        this.tgInspecteur           .setPreferredSize(new Dimension(45, 40));
        this.tgInspecteur           .setFont(new Font("Sans-Serif", Font.BOLD, 18));
        this.tgInspecteur           .setForeground(Color.BLACK);
        this.tgInspecteur           .setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        this.tgInspecteur           .setHorizontalAlignment(SwingConstants.RIGHT);
        this.tgInspecteur           .setOpaque(false);
        this.tgInspecteur           .setContentAreaFilled(false); // Supprime le fond peint par le Look & Feel
        this.tgInspecteur           .setBorderPainted(false);     // Supprime le contour du bouton
        this.tgInspecteur           .setFocusPainted(false);      // Supprime le cadre de focus au clic
        this.tgInspecteur           .addActionListener(this);

        menuFichier.add(this.itemOuvrir);
        menuFichier.addSeparator();
        menuFichier.add(this.itemEnregister);
        menuFichier.addSeparator();
        menuFichier.add(this.itemEnregistrerSous);
        menuFichier.addSeparator();
        menuFichier.add(this.itemExporter);

        menuEdition.add(this.itemAfficherInspecteur);

        barreMenu.add(menuFichier);
        barreMenu.add(menuEdition);
        barreMenu.add(menuAffichage);
        barreMenu.add(Box.createHorizontalGlue());
        barreMenu.add(this.tgInspecteur);

        // On applique la barre à la fenêtre
        this.setJMenuBar(barreMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.itemOuvrir)
        {
            JFileChooser fcFichier = new JFileChooser("."); // Création du sélectionneur de fichier
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers XML", "xml");
			fcFichier.setFileSelectionMode(fcFichier.FILES_ONLY); 
			fcFichier.setFileFilter(filter);
            fcFichier.setDialogTitle("Ouvrir un fichier XML");
            fcFichier.setApproveButtonText("Ouvrir");
            fcFichier.setApproveButtonToolTipText("Ouvrir le fichier XML");
            fcFichier.setAcceptAllFileFilterUsed(false);

			int res = fcFichier.showOpenDialog(this);

            // Si on appuie sur Ouvrir en ayant choisi son répertoire
			if (res == JFileChooser.APPROVE_OPTION)
			{
                if (fcFichier.getSelectedFile().getName().toLowerCase().endsWith(".xml") && fcFichier.getSelectedFile().exists())
                {
                    this.ctrl.ouvrirFichierXML(fcFichier.getSelectedFile());
                }
                else
                {
                    System.out.println("Erreur ouverture fichier : " + fcFichier.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Veuillez sélectionner un fichier XML valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource() == this.itemEnregister)
        {
            this.ctrl.enregistrerFichier();
        }
        else if (e.getSource() == this.itemEnregistrerSous)
        {
            JFileChooser fcFichier = new JFileChooser("."); // Création du sélectionneur de fichier
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers XML", "xml");
			fcFichier.setFileSelectionMode(fcFichier.FILES_ONLY); 
			fcFichier.setFileFilter(filter);
            fcFichier.setAcceptAllFileFilterUsed(false);
            fcFichier.setDialogTitle("Enregistrer sous...");
            fcFichier.setApproveButtonText("Enregistrer");
            fcFichier.setApproveButtonToolTipText("Enregistrer le fichier XML");
			
			int res = fcFichier.showSaveDialog(this);

            // Si on appuie sur Ouvrir en ayant choisi son répertoire
			if (res == JFileChooser.APPROVE_OPTION)
			{
                File fichierSelectionne = fcFichier.getSelectedFile();
                    
                if (fichierSelectionne != null && !fcFichier.getSelectedFile().getName().toLowerCase().endsWith(".xml"))
                {
                    fcFichier.setSelectedFile(new File(fichierSelectionne.getAbsolutePath() + ".xml"));
                }
                
                if (fcFichier.getSelectedFile().exists())
                {   
                    int option = JOptionPane.showConfirmDialog(this, "Le fichier existe déjà. Voulez-vous le remplacer ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.YES_OPTION == option)
                    {
                        this.ctrl.enregistrerFichierSous(fcFichier.getSelectedFile());
                    }
                    else
                    {
                        System.out.println("Enregistrement annulé");
                    }
                }
                else
                {
                    this.ctrl.enregistrerFichierSous(fcFichier.getSelectedFile());
                }
            }
        }
        else if (e.getSource() == this.itemExporter)
        {
            JFileChooser fcFichier = new JFileChooser("."); // Création du sélectionneur de fichier
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers PDF", "pdf");
			fcFichier.setFileSelectionMode(fcFichier.FILES_ONLY); 
			fcFichier.setFileFilter(filter);
            fcFichier.setSelectedFile(new File("diagramme.pdf"));
            fcFichier.setDialogTitle("Exporter en PDF");
            fcFichier.setApproveButtonText("Exporter");
            fcFichier.setApproveButtonToolTipText("Exporter le diagramme en PDF");
            fcFichier.setAcceptAllFileFilterUsed(false);

			int res = fcFichier.showSaveDialog(this);

            // Si on appuie sur Ouvrir en ayant choisi son répertoire
			if (res == JFileChooser.APPROVE_OPTION)
			{
                File fichierSelectionne = fcFichier.getSelectedFile();
                
                if (fichierSelectionne != null && !fichierSelectionne.getName().toLowerCase().endsWith(".pdf"))
                {
                    fcFichier.setSelectedFile(new File(fichierSelectionne.getAbsolutePath() + ".pdf"));
                }
                
                if (fcFichier.getSelectedFile().exists())
                {
                    int option = JOptionPane.showConfirmDialog(this, "Le fichier existe déjà. Voulez-vous le remplacer ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.YES_OPTION == option)
                    {
                        this.ctrl.exporterEnPDF(fcFichier.getSelectedFile());
                    }
                    else
                    {
                        System.out.println("Exportation annulée");
                    }
                }
                else
                {
                    this.ctrl.exporterEnPDF(fcFichier.getSelectedFile());
                }
            }
            
        }
        else if (e.getSource() == this.itemAfficherInspecteur)
        {
            boolean estVisible = this.itemAfficherInspecteur.isSelected();

            System.out.println("(JCheckBoxMenuItem) Inspecteur visible : " + estVisible);

            if (estVisible)
            {
                this.tgInspecteur.setForeground(Color.BLUE);
                this.tgInspecteur.setToolTipText("Masquer l'inspecteur");
            }
            else
            {
                this.tgInspecteur.setForeground(Color.BLACK);
                this.tgInspecteur.setToolTipText("Afficher l'inspecteur");
            }

            this.tgInspecteur.setSelected(estVisible);
            this.pnlActif.setVisiblePnlDroit(estVisible);

            this.revalidate();
            this.repaint();
        }
        else if (e.getSource() == this.tgInspecteur)
        {
            boolean estVisible = this.tgInspecteur.isSelected();

            System.out.println("(JToggleButton) Inspecteur visible : " + estVisible);

            if (estVisible)
            {
                this.tgInspecteur.setForeground(Color.BLUE);
                this.tgInspecteur.setToolTipText("Masquer l'inspecteur");
            }
            else
            {
                this.tgInspecteur.setForeground(Color.BLACK);
                this.tgInspecteur.setToolTipText("Afficher l'inspecteur");
            }

            this.itemAfficherInspecteur.setSelected(estVisible);
            this.pnlActif.setVisiblePnlDroit(estVisible);

            this.revalidate();
            this.repaint();
        }
    }
}