import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class mouseInput extends MouseAdapter {

    spriteSheet ss;
    Game game;
    playCard pCard;
    ArrayList<Card> hand;
    ArrayList<Card> other;


    public mouseInput(Game game, spriteSheet ss, playCard pCard, ArrayList<Card> hand, ArrayList<Card> other) {
        this.game = game;
        this.ss = ss;
        this.pCard = pCard;
        this.hand = hand;
        this.other = other;
    }

    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1 && other.size() != 0 && hand.size() != 0) {
            pCard.setAwait(!pCard.getAwait());
        }
    }

    public void mouseReleased(MouseEvent e) {
    }
    
}
