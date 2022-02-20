import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInput extends KeyAdapter{

    handler handler;
    projectile projectile;
    private int curr = 0;

    public keyInput(handler handler, projectile projectile) {
        this.handler = handler;
        this.projectile = projectile;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++) {
            gameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == id.Character) {
                if (key == KeyEvent.VK_W) handler.setUp(true);
                if (key == KeyEvent.VK_S) handler.setDown(true);
                if (key == KeyEvent.VK_A) handler.setLeft(true);
                if (key == KeyEvent.VK_D) handler.setRight(true);
                if (key == KeyEvent.VK_F) {
                    if (!(curr == tempObject.getWepList()[0].length - 1)) {
                        System.out.println("switching " + tempObject.getWepList()[0].length);
                        for (int j = 0; j < tempObject.getWepList()[0].length; j++) {
                            if (tempObject.getWepList()[0][j] == tempObject.getWep()) {
                                curr++;
                                String temp = (String) tempObject.getWepList()[0][j + 1];
                                tempObject.setWep(temp); 
                                System.out.println("switched to " + temp + " " + curr);
                                break;
                            }
                        }
                    } else {
                        curr = 0;
                        tempObject.setWep((String) tempObject.getWepList()[0][0]);
                        System.out.println("switched to " + tempObject.getWepList()[0][0]);
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++) {
            gameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == id.Character) {
                if (key == KeyEvent.VK_W) handler.setUp(false);
                if (key == KeyEvent.VK_S) handler.setDown(false);
                if (key == KeyEvent.VK_A) handler.setLeft(false);
                if (key == KeyEvent.VK_D) handler.setRight(false);
            }
        }
    }
}
