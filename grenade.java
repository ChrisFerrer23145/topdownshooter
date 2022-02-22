import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class grenade extends projectile{

    private int mx, my, counter;

    handler handler;
    private BufferedImage grenade;

    public grenade(int posx, int posy, int health, id id, handler handler, int mx, int my, spriteSheet ss) {
        super(posx, posy, health, id, ss);
        this.mx = mx;
        this.my = my;
        this.handler = handler;
        counter = 180;

        angle(posx, posy, mx, my);
        grenade = ss.grabImage(32, 10, 20, 20);

        handler.setGtimer(300);
        
    }

    public void tick() {
        System.out.println(handler.grenadeTimer());
        posx += velx;
        posy += vely;

        collision();

        if (Math.abs(posx - mx) < 20 && Math.abs(posy - my) < 20) {
            velx = 0;
            vely = 0;
        }

        counter--;
        if (counter <= 0) {
            detonate();
            handler.gExplode(posx, posy, ss);
            handler.removeObject(this);
        }

        
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
    public void render(Graphics g) {
        g.drawImage(grenade, posx, posy, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 20, 20);
    }

    private void detonate() {
        ArrayList<gameObject> temp = new ArrayList<gameObject>();
        for(int i = 0; i < handler.object.size(); i++){
            gameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == id.Enemy){
                if (Math.abs(posx - tempObject.getx()) < 300 && Math.abs(posy - tempObject.gety()) < 300) {
                    temp.add(tempObject);
                }
            }
        }

        for(int i = 0; i < temp.size(); i++){
            gameObject tempObject = temp.get(i);
            if(tempObject.getId() == id.Enemy){
                tempObject.setHealth(tempObject.getHealth() - 75);
            }
        }

    }
    
}