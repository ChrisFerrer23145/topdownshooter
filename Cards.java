import java.util.ArrayList;

public class Cards {
    private ArrayList<Card> cardList = new ArrayList<Card>();
    public Cards(spriteSheet css, spriteSheet ss) {
        String suit;
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                suit = "Clubs";
            } else if (i == 1) {
                suit = "Spades";
            } else if (i == 2) {
                suit = "Hearts";
            } else {
                suit = "Diamonds";
            }
            for (int j = 0; j < 13; j++) {
                cardList.add(new Card(suit, j + 1, j*71, i*96, css, ss));
            }
        }
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }
}
