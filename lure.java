import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class lure extends projectile {

    handler handler;
    private int mx;
    private int my;
    private int counter = 900;
    private BufferedImage lure;

    public lure(int posx, int posy, int health, id id, handler handler, int mx, int my, spriteSheet ss) {
        super(posx, posy, health, id, ss);
        this.handler = handler;
        this.mx = mx;
        this.my = my;

        angle(posx, posy, mx, my);
        lure = ss.grabImage(32, 0, 10, 10);
        handler.setTimer(600);
    }
    
    
    public void tick() {

        posx += velx;
        posy += vely;

        collision();

        if (Math.abs(posx - mx) < 20 && Math.abs(posy - my) < 20) {
            velx = 0;
            vely = 0;
        }

        counter--;
        if (counter <= 0) {
            handler.removeObject(this);
        }

        
    }

    public void render(Graphics g) {
        g.drawImage(lure, posx, posy, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 10, 10);
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
                    velx += velx * -1;
                    vely += vely * -1;
                }
            }
        }
    }


    public void angle(int posx, int posy, int mx, int my) {

        int j = my - posy;
        int f = mx - posx;
        double g = Math.sqrt(j * j + f * f);
        double ang = Math.asin((double) Math.abs(j)/ (double) Math.abs(g));
        
        velx = (int) (g/20);
        vely = (int) (g/20);
        
        if (j > 0  && f > 0) {
        }
        if (j > 0  && f < 0) {
            velx *= -1;
        }
        if (j < 0  && f < 0) {
            velx *= -1; 
            vely *= -1;
        }
        if (j < 0  && f > 0) {
            vely *= -1;
        }
        System.out.println(ang);
        velx *= Math.cos(ang);
        vely *= Math.sin(ang);

    }
    
}
