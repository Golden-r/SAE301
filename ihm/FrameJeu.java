package ihm;

import controleur.Controleur;

import java.awt.JPanel;
import java.awt.JFrame;
import java.awt.BorderLayout;

import javax.swing.*;

public class FrameJeu extends JFrame
{
    private Controleur  ctrl;
    private JPanel      pnlActif;
    
    public FrameJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.pnlActif = new PanelGenerateur(ctrl, this);

        this.setTitle("Générateur de diagrammes de classe à partir d'un fichier XML");
        this.setSize(800,800);
        this.setLayout(new BorderLayout());

        this.add(pnlActif, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }
}