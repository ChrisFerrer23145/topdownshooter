import java.awt.Graphics;
import java.util.ArrayList;

public class playCard {

    private boolean await;
    private ArrayList<Card> table;
    private ArrayList<Card> hand;
    private ArrayList<Card> other; 
    public playCard(ArrayList<Card> table, ArrayList<Card> hand, ArrayList<Card> other) {
        await = true;
        this.table = table;
        this.hand = hand;
        this.other = other;
    }

    public void render(Graphics g) {
        for (int i = 0; i < table.size(); i++) {
            switch (i % 8) {
                case 0:
                    g.drawImage(table.get(i).getSprite(), 445, 401 - 96 - 15, null);
                    break;
                case 1: 
                    g.drawImage(table.get(i).getSprite(), 445, 29 + 96 + 15, null);
                    break;
                case 2: 
                    g.drawImage(table.get(i).getBack(), 445 - 86, 401, null);
                    break;
                case 3: 
                    g.drawImage(table.get(i).getBack(), 445 - 172, 401, null);
                    break;
                case 4: 
                    g.drawImage(table.get(i).getBack(), 445 - 258, 401, null);
                    break;
                case 5: 
                    g.drawImage(table.get(i).getBack(), 445 - 86, 29, null);
                    break;
                case 6: 
                    g.drawImage(table.get(i).getBack(), 445 - 172, 29, null);
                    break;
                case 7: 
                    g.drawImage(table.get(i).getBack(), 445 - 258, 29, null);
                    break;
            } 
        }
    }

    public void setAwait(boolean awa) {
        if (await != awa) {
            if (await == false) {
                int s = table.size();
                if (s > 0) {
                    if (table.get(table.size()-2).getValue() == table.get(table.size() - 1).getValue()) {
                        table.add(hand.get(s/2 + 1)); // 2
                        table.add(hand.get(s/2 + 2)); // 3
                        table.add(hand.get(s/2 + 3)); // 4
                        table.add(other.get(s/2 + 1)); // 5
                        table.add(other.get(s/2 + 2)); // 6
                        table.add(other.get(s/2 + 3)); // 7
                        table.add(hand.get(s/2 + 4)); // 0
                        table.add(other.get(s/2 + 4)); // 1
                    } else if ((table.get(table.size()-2).getValue() > table.get(table.size() - 1).getValue() || table.get(table.size()-2).getValue() == 1) && table.get(table.size() - 1).getValue() != 1) {
                        for (int i = 0; i < table.size(); i++) {
                            switch (i % 8) {
                                case 0:
                                    hand.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                                case 1:
                                    other.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                                case 2:
                                    hand.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                                case 3:
                                    hand.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                                case 4:
                                    hand.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                                case 5:
                                    other.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                                case 6:
                                    other.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));    
                                    break;
                                case 7:
                                    other.remove(0);
                                    table.get(i).setHand("hand");
                                    hand.add(table.get(i));
                                    break;
                            }  
                        }
                        table.clear();
                        await = awa;
                    } else {
                        for (int i = 0; i < table.size(); i++) {
                            switch (i % 8) {
                                case 0:
                                    hand.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                                case 1:
                                    other.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                                case 2:
                                    hand.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                                case 3:
                                    hand.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                                case 4:
                                    hand.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                                case 5:
                                    other.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                                case 6:
                                    other.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));    
                                    break;
                                case 7:
                                    other.remove(0);
                                    table.get(i).setHand("other");
                                    other.add(table.get(i));
                                    break;
                            }  
                        }
                        table.clear();
                        await = awa;
                    }
                }
            } else if (awa == false) {
                table.add(hand.get(0));
                table.add(other.get(0));
                await = awa;
            }
        }
    }
    public boolean getAwait() {
        return await;
    }
    public ArrayList<Card> getTable() {
        return table;
    }
}
