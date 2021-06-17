import Patterns.Observateur;

import javax.swing.*;

public class MonApplication {
    Jeu jeu;
    NiveauGraphique ng;
    JFrame frame;

    public static void demarre(JFrame frame){
        Jeu j = new Jeu();
        new MonApplication(frame, j);
    }

    MonApplication(JFrame frame, Jeu j){
        jeu = j;
        this.frame = frame;
        initialiseFrame();

    }

    public void initialiseFrame(){
        ng = new NiveauGraphique(jeu);
        frame.add(ng);
        AnimationPousseur anim = new AnimationPousseur(ng);
        frame.addKeyListener(new AdaptateurClavier(anim));
    }
}
