import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouseInput extends MouseAdapter {

    handler handler;
    camera camera;
    spriteSheet ss;
    Game game;

    public mouseInput(handler handler, camera camera, Game game, spriteSheet ss) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.ss = ss;
    }

    public void mousePressed(MouseEvent e) {
        int mx = (int) e.getX() + (int) camera.getX();
        int my = (int) e.getY() + (int) camera.getY();
        
        for (int i = 0; i < handler.object.size(); i++) {
            gameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == id.Character) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (tempObject.wep == "bullet") {
                        handler.addObject(new bullet(tempObject.getx() + 16, tempObject.gety() + 24, Integer.MAX_VALUE, id.Bullet, handler, mx, my, ss));
                    } else if (tempObject.wep == "lure" && handler.lureTimer() == 0) {
                        System.out.println(handler.lureTimer());
                        handler.addObject(new lure(tempObject.getx() + 16, tempObject.gety() + 24, Integer.MAX_VALUE, id.Lure, handler, mx, my, ss));
                    } else if (tempObject.wep == "grenade" && handler.grenadeTimer() == 0) {
                        System.out.println(handler.grenadeTimer());
                        handler.addObject(new grenade(tempObject.getx() + 16, tempObject.gety() + 24, Integer.MAX_VALUE, id.Grenade, handler, mx, my, ss));

                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        
        for (int i = 0; i < handler.object.size(); i++) {
            gameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == id.Character) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                }
            }
        }
    }
    
}
