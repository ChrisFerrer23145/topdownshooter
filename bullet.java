import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class bullet extends projectile{

    handler handler;

    public bullet(int posx, int posy, int health, id id, handler handler, int mx, int my, spriteSheet ss) {
        super(posx, posy, health, id, ss);
        this.handler = handler;

        angle(posx, posy, mx, my);
    }

    public void tick() {
        posx += velx;
        posy += vely;

        collision();
        
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(posx, posy, 8, 8);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 8, 8);
    }

    public void collision() {
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
                    handler.removeObject(this);
                }
            }
        }
    }

    public void angle(int posx, int posy, int mx, int my) {
        int j = my - posy;
        int f = mx - posx;
        double g = Math.sqrt(j * j + f * f);
        double ang = Math.asin((double) Math.abs(j)/ (double) Math.abs(g));
        
        velx = 10;
        vely = 10;
        
        int q = 0;
        if (j > 0  && f > 0) {
            q = 4;
        }
        if (j > 0  && f < 0) {
            q = 3; 
            velx *= -1;
        }
        if (j < 0  && f < 0) {
            q = 2; 
            velx *= -1; 
            vely *= -1;
        }
        if (j < 0  && f > 0) {
            q = 1; 
            vely *= -1;
        }
        
        velx *= Math.cos(ang);
        vely *= Math.sin(ang);
        System.out.println(Math.toDegrees(ang) + " " + velx + " " + vely + " " + q);
        
        
    }
    
}
