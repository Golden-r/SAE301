package controleur;

import ihm.FrameJeu;

import java.io.File;

public class Controleur
{
    private FrameJeu frame;

    public Controleur()
    {
        this.frame = new FrameJeu(this);
    }

    public void ouvrirFichierXML(File fichier)
    {
        System.out.println("Ouverture : " + fichier.getAbsolutePath());
    }

    public void enregistrerFichier()
    {
        System.out.println("Enregistrement rapide");
    }

    public void enregistrerFichierSous(File fichier)
    {
        System.out.println("Enregistrement sous : " + fichier.getAbsolutePath());
    }

    public void exporterEnPDF(File fichier)
    {
        System.out.println("Exportation en PDF : " + fichier.getAbsolutePath());
    }

    public static void main(String[] args)
    {
        Controleur ctrl = new Controleur();
    }
}