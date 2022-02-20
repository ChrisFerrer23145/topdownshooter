import java.awt.Graphics;
import java.awt.Rectangle;

public class projectile extends gameObject{

    handler handler;

    public projectile(handler handler) {
        super(handler);
        this.handler = handler;

    }

    public projectile(int posx, int posy, int health, id id, spriteSheet ss) {
        super(posx, posy, health, id, ss);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        
    }

    public Rectangle getBounds() {
        return null;
    }
}
