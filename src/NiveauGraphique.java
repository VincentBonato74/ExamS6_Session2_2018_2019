import Structures.Iterateur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Iterator;

public class NiveauGraphique extends JComponent {
    Jeu jeu;
    int largeur, hauteur, tailleElem;
    Graphics2D drawable;
    Image pousseur, caisse;
    Point position;

    public Image chargeImage(String nom){
        Image img = null;
        try{
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(nom + ".png");
            if(in != null){
                img = ImageIO.read(in);
            }
        }catch (Exception e){
            System.err.println("Erreur lors du chargement de l'image : " + e);
            System.exit(1);
        }
        return img;
    }

    public NiveauGraphique(Jeu j){
        jeu = j;
        pousseur = chargeImage("Pousseur");
        caisse = chargeImage("Caisse");
    }

    public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        largeur = getSize().width;
        hauteur = getSize().height;

        jeu.largeur = largeur;
        jeu.ng = this;

        drawable.clearRect(0, 0, largeur, hauteur);

        int tailleLargeur = (int)Math.round(largeur * 0.1);
        int tailleHauteur = (int)Math.round(hauteur * 0.1);
        tailleElem = Math.min(tailleLargeur, tailleHauteur);

        if(position == null){
            position = new Point(0, hauteur-tailleElem);
        }

        drawable.drawImage(pousseur, (int)Math.round(largeur*0.1), position.y, tailleElem, tailleElem, null);

        if(jeu.estSortie(position.y)){
            System.out.println("Perdu tu es sortie en haut !");
            System.exit(1);
        }


        Iterateur it = jeu.caisses.iterateur();
        Caisse actuel;
        while(it.aProchain()){
            actuel = (Caisse) it.prochain();
            if(actuel.posX <= -tailleElem){
                it.supprime();
                System.out.println("Je suis sortie");
            }
            if(jeu.touche((int)Math.round(largeur*0.1), position().y, actuel.posX, hauteur - tailleElem, tailleElem)){
                System.out.println("Perdu");
                System.exit(1);
            }
            drawable.drawImage(caisse, actuel.posX, hauteur - tailleElem, tailleElem, tailleElem, null);
        }
    }

    public void fixePosition(int x, int y) {
        position = new Point(0, (hauteur-tailleElem)-y);
        repaint();
    }

    public Point position() {
        return position;
    }
}

