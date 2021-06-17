package Controler;

import Vue.NiveauGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationPousseur implements ActionListener {

    NiveauGraphique ng;
    Point depart, arrivee;
    Timer temps;
    double progres, vitesseAnim, vitesseChute, vitesseMonte;
    boolean retombe, EnAir, EnTrainDeTomber;
    int posY;

    public AnimationPousseur(NiveauGraphique n){
        ng = n;
        temps = new Timer(16, this);
        vitesseAnim = 0.2;
        vitesseChute = 1;
        vitesseMonte = 10;
        EnAir = false;
        posY = 0;
    }

    void space(int x, int y){
        if(!EnAir){
            arrivee = new Point(x, y);
            depart = new Point(0, 0);
            temps.start();
            progres = 0;
            retombe = false;
            EnTrainDeTomber = false;
            EnAir = true;
        }
    }

    void retombe(int x, int y){
        arrivee = new Point(0, 0);
        depart = new Point(0, y);
        temps.start();
        progres = 0;
        vitesseChute = 0.1;
        retombe = false;
        EnTrainDeTomber = true;
        EnAir = true;
    }

    public void monte(){
        temps.start();
        retombe = false;
        EnTrainDeTomber = false;
        EnAir = true;
    }

    public void descend(){
        temps.start();
        vitesseChute = 1              ;
        retombe = false;
        EnTrainDeTomber = true;
        EnAir = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(EnTrainDeTomber){
            posY = (int) Math.round(posY - vitesseChute);
            vitesseChute = vitesseChute*1.5;
            if(posY <= 0){
                posY = 0;
                temps.stop();
            }
        }else{
            posY = (int) Math.round(posY + vitesseMonte);
        }

        /*if(progres > 1){
            if(EnTrainDeTomber){
                retombe = false;
                EnAir = false;
                temps.stop();
            }else{
                retombe = true;
                EnAir = true;
                temps.stop();
            }
            progres = 1;
        }*/
        //int y = (int) Math.round((1-progres)*depart.y + progres*arrivee.y);
        ng.fixePosition(0, posY);
        /*if(retombe){
            retombe(0, arrivee.y);
        }*/
    }
}
