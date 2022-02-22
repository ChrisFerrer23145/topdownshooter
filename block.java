import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class block extends gameObject {

    handler handler;
    private BufferedImage wall;

    public block(int posx, int posy, int health, id id, spriteSheet ss) {
        super(posx, posy, health, id, ss);

        wall = ss.grabImage(64, 32, 32, 32);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(wall, posx, posy, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 32, 32);
    }

}
