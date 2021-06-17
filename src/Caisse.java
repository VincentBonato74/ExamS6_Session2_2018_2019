import Patterns.Observable;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Caisse extends Observable implements ActionListener {
    int PositionDep;
    int posX;
    int destinationX;
    double progres, vitesseAnim;
    Timer caisse;

    Caisse(int posXNiv){
        PositionDep = posXNiv;
        posX = posXNiv;
        vitesseAnim = 0.03;
        caisse = new Timer(16, this);
        caisse.start();
        progres = 0;
        destinationX = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        progres += vitesseAnim;
        if(progres > 1.2){
            caisse.stop();
            progres = 1.2;
        }
        int x = (int) Math.round((1-progres)*PositionDep);
        posX = x;
        miseAJour();
    }
}
