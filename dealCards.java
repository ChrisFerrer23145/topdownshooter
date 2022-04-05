import java.util.ArrayList;

public class dealCards {
    spriteSheet ss;
    ArrayList<Card> hand;
    ArrayList<Card> other;
    Cards cards;
    public dealCards(spriteSheet spritesheet, ArrayList<Card> hand, ArrayList<Card> other, Cards cards) {
        this.ss = spritesheet;
        this.hand = hand;
        this.other = other;
        this.cards = cards;
        setCards();
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public ArrayList<Card> getOther() {
        return other;
    }

    public void setCards() {
        int size = cards.getCardList().size();
        for (int i = 0; i < size; i++) {
            int ran =  (int) (Math.random() * size);
            if (hand.size() == 26) {
                other.add(cards.getCardList().get(ran).setHand("other"));
            } else {
                hand.add(cards.getCardList().get(ran).setHand("hand"));
            }
            cards.getCardList().remove(ran);
            size--;
            i--;
        }
    }
}
