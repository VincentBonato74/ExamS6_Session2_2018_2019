import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AdaptateurClavier implements KeyListener {
    AnimationPousseur anim;

    public AdaptateurClavier(AnimationPousseur a){
        anim = a;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() ==  KeyEvent.VK_SPACE){
            //anim.space(0, 200 );
            anim.monte();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        anim.descend();
    }
}
