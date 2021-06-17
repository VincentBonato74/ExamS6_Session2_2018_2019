import Patterns.Observateur;

import javax.swing.*;

public class Examen implements Runnable {

    @Override
    public void run() {
        JFrame frame = new JFrame("Exam S6");

        MonApplication.demarre(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Examen());
    }
}
