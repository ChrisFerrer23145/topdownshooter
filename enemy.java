import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class enemy extends gameObject{

    private handler handler;
    private int num = 0;
    private int seed = 0;
    private int aggro = 1000;
    private BufferedImage ene;

    public enemy(int posx, int posy, int health, id id, handler handler, spriteSheet ss) {
        super(posx, posy, health, id, ss);
        this.handler = handler;

        ene = ss.grabImage(4, 1, 32, 32);
    }

    public void tick() {

        movement();

        posx += velx;
        posy += vely;

        collision();
        if (this.health <= 0) {
            handler.removeObject(this);
        }

    }

    public void render(Graphics g) {
        g.drawImage(ene, posx, posy, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 32, 32);
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
                    posx += velx * -1;
                    posy += vely * -1;
                }
            }
            if (tempObject.getId() == id.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    this.health -= 10;
                    aggro = 0;
                    
                }
            }
        }
        
    }

    public void movement() {
        for (int i = 0; i < handler.object.size(); i++) {
            gameObject tempObject;
            try {
                tempObject = handler.object.get(i);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
            if (tempObject.getId() == id.Character) {
                int x = tempObject.getx();
                int y = tempObject.gety();
                int xd = posx - x;
                int yd = posy - y;
                double distance = Math.sqrt((xd * xd) + (yd * yd));
                if (distance < 250 || aggro < 300) {
                    velx = (int) ((xd / distance) * 5) * -1;
                    vely = (int) ((yd / distance) * 5) * -1;
                    aggro++;
                } else {
                    if (num % 20 == 0) {
                        seed = (int) (Math.random() * 360);
                    }
                    velx = (int) (Math.cos(Math.toRadians(seed)) * 4);
                    vely = (int) (Math.sin(Math.toRadians(seed)) * 4);
                    
                    num++;

                }

            }
            if(tempObject.getId() == id.Lure) {
                int x = tempObject.getx();
                int y = tempObject.gety();
                int xd = posx - x;
                int yd = posy - y;
                double distance = Math.sqrt((xd * xd) + (yd * yd));

                boolean charac = false;
                for (int j = 0; j < handler.object.size(); j++) {
                    gameObject tempObject2;
                    try {
                        tempObject2 = handler.object.get(j);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    if (tempObject2.getId() == id.Character) {
                        int x2 = tempObject2.getx();
                        int y2 = tempObject2.gety();
                        int xd2 = posx - x2;
                        int yd2 = posy - y2;
                        double distance2 = Math.sqrt((xd2 * xd2) + (yd2 * yd2));
                        if (distance2 < 250) {
                            charac = true;
                        }
                    }
                }
                if (distance < 300 || aggro < 300) {
                    velx = (int) ((xd / distance) * 5) * -1;
                    vely = (int) ((yd / distance) * 5) * -1;
                    aggro++;
                } else if (!charac) {
                    if (num % 40 == 0) {
                        seed = (int) (Math.random() * 360);
                    }
                    velx = (int) (Math.cos(Math.toRadians(seed)) * 4);
                    vely = (int) (Math.sin(Math.toRadians(seed)) * 4);
                    
                    num++;

                }
            }
        }
    }

}
