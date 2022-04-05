import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Game extends Canvas implements Runnable {
    private boolean running = false;
    private Thread thread;
    private spriteSheet css;
    private spriteSheet ss;
    private BufferedImage cardsSpriteSheet = null;
    private BufferedImage spriteSheet = null;
    private dealCards dealCards;
    private Cards cards;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Card> other = new ArrayList<Card>();
    private ArrayList<Card> table = new ArrayList<Card>();
    private playCard playCard;
    public static void main(String[] args) throws Exception {
        new Game();
    }

    public Game() {
        new window(1000, 563, "War", this);
        
        imgLoader loader = new imgLoader();
        cardsSpriteSheet = loader.loadImage("src/cards.png");
        spriteSheet = loader.loadImage("src/spritesheet.png");
        css = new spriteSheet(cardsSpriteSheet);
        ss = new spriteSheet(spriteSheet);
        cards = new Cards(css, ss);
        dealCards = new dealCards(css, hand, other, cards);
        playCard = new playCard(table, hand, other);
        this.addMouseListener(new mouseInput(this, css, playCard, hand, other));
        
        
        start();
    }

    private void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        long orig = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                // updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                // updates = 0;
            }
        }
        stop();
    }

    public void tick() {
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(java.awt.Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (hand.size() > 0) {
            g.drawImage(hand.get(0).getBack(), this.getWidth()/2 - 48, 401, null);
        }
        if (other.size() > 0) {
            g.drawImage(other.get(0).getBack(), this.getWidth()/2 - 48, (int) ((this.getHeight()/ 8) - 36), null);
        }
        playCard.render(g);

        g.setColor(Color.black);
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
        
        g.drawString("Cards left (hand): " + hand.size(), 531, 457);
        g.drawString("Cards left (other): " + other.size(), 531, 85);
        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);

        g.dispose();
        bs.show();
    }
}