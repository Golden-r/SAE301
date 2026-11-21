package ihm;

import controleur.Controleur;

import java.awt.JPanel;
import java.awt.JFrame;
import java.awt.BorderLayout;

import javax.swing.*;

public class PanelGenerateur extends JPanel
{
    private Controleur      ctrl;
    private FrameJeu        frame;

    private JPanel          pnlGauche;
    private JPanel          pnlDroit ;
    private JPanel          pnlCentre;

    public PanelGenerateur(Controleur ctrl, FrameJeu frame)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setLayout(new BorderLayout());

        this.pnlGauche  = new JPanel(new BorderLayout());
        this.pnlGauche  .setOpaque(false);

        this.pnlDroit   = new JPanel(new BorderLayout());
        this.pnlDroit   .setOpaque(false);

        this.pnlCentre  = new JPanel(new BorderLayout());
        this.pnlCentre  .setOpaque(false);
    }
}
