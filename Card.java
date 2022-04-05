import java.awt.image.BufferedImage;

public class Card {
    private String suit;
    private int value;
    private String hand;
    public BufferedImage image;
    private BufferedImage back;
    private int x, y;
    private spriteSheet css, ss;

    public Card(String suit, int value, int x, int y, spriteSheet css, spriteSheet ss) {
        this.suit = suit;
        this.value = value;
        this.x = x;
        this.y = y;
        this.css = css;
        this.ss = ss;
        image = css.grabImage(x, y, 71, 96);
    }

    public String getSuit() {
        return suit;
    }
    
    public int getValue() {
        return value;
    }

    public Card setHand(String handType) {
        this.hand = handType;
        if (handType == "hand") {
            back = ss.grabImage(0, 0, 71, 96);
        } else {
            back = ss.grabImage(71, 0, 71, 96);
        }
        return this;
    }

    public BufferedImage getSprite() {
        return image;
    }

    public BufferedImage getBack() {
        return back;
    }

    public String getHand() {
        return hand;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
