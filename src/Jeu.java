import Patterns.Observable;
import Patterns.Observateur;
import Structures.Sequence;
import Structures.SequenceListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jeu implements ActionListener, Observateur {
    public int largeur;
    NiveauGraphique ng;
    Sequence<Caisse> caisses;
    Timer ajout;

    public Jeu(){
        caisses = new SequenceListe<Caisse>();
        ajout = new Timer(3000, this);
        ajout.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Caisse caisse = new Caisse(largeur);
        caisse.ajoutObservateur(this);
        caisses.insereTete(caisse);
    }

    public boolean touche(int pX, int pY, int cX, int cY, int tailleElem){
        if((cX >= pX) && (cX <= pX+tailleElem)){
            if((cY >= pY) && (cY <= pY+tailleElem)){
                return true;
            }
        }
        return false;
    }

    public boolean estSortie(int pY){
        if(pY <= 0){
            return true;
        }
        return false;
    }

    @Override
    public void metAJour() {
        ng.repaint();
    }
}
