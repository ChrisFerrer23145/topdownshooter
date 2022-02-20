import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class character extends gameObject{

    handler handler;
    public Object[][] weapons = {{"bullet", "lure", "grenade"}, {id.Bullet, id.Lure, id.Grenade}};
    public String weapon = "bullet";
    private BufferedImage character;

    public character(int posx, int posy, int health, id id, handler handler, spriteSheet ss) {
        super(posx, posy, health, id, ss);
        super.weapon(weapon, weapons);
        this.handler = handler;
        character = ss.grabImage(1, 1, 32, 48);

    }

    public void tick() {
        posx += velx;
        posy += vely;

        collision();

        if (health <= 0) {
            handler.setDown(false);
            handler.setUp(false);
            handler.setLeft(false);
            handler.setRight(false);
        }

        if (handler.getUp()) vely = -5;
        else if (!handler.getDown()) vely = 0;

        if (handler.getDown()) vely = 5;
        else if (!handler.getUp()) vely = 0;

        if (handler.getLeft()) velx = -5;
        else if (!handler.getRight()) velx = 0;

        if (handler.getRight()) velx = 5;
        else if (!handler.getLeft()) velx = 0;

    }

    public void render(Graphics g) {
        g.drawImage(character, posx, posy, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 32, 48);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            gameObject tempObject;
            try {
                tempObject = handler.object.get(i);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            if (tempObject.getId() == id.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    posx += velx * -1;
                    posy += vely * -1;
                }
            }
            if (tempObject.getId() == id.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    this.health -= 1;
                }
            }
        }
    }

    public String getWep() {
        return wep;
    }

    public void setWep(String wep) {
        this.wep = wep;
    }
    
}
