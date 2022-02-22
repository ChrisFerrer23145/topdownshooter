import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class handler {
    LinkedList<gameObject> object = new LinkedList<gameObject>();

    private boolean up = false, down = false, left = false, right = false;
    private int timer = 0;
    private int gtimer = 0;
    spriteSheet ss;
    private int extimer = 0;
    private int exx = 0, exy = 0;
    private BufferedImage explode1;
    private BufferedImage explode2;
    private BufferedImage explode3;


    public void tick() {
        for(int i = 0; i < object.size(); i++){
            gameObject tempObject = object.get(i);
            tempObject.tick();
        }
        if (grenadeTimer() > 0) setGtimer(grenadeTimer() - 1);
        if (lureTimer() > 0) setTimer(lureTimer() - 1);
    }

    public void render(Graphics g) {
        for(int i = 0; i <object.size(); i++){
            gameObject tempObject = object.get(i);
            tempObject.render(g);
        }

        if (extimer > 0) {
            g.drawImage(explode1, exx - 32, exy - 32, null);
            g.drawImage(explode2, exx, exy - 32, null);
            g.drawImage(explode3, exx + 32, exy - 32, null);

            g.drawImage(explode2, exx - 32, exy, null);
            g.drawImage(explode3, exx, exy, null);
            g.drawImage(explode1, exx + 32, exy, null);

            g.drawImage(explode3, exx - 32, exy + 32, null);
            g.drawImage(explode1, exx, exy + 32, null);
            g.drawImage(explode2, exx + 32, exy + 32, null);
            extimer--;
        }

    }

    public void addObject(gameObject tempObject){
        object.add(tempObject);
    }

    public void removeObject(gameObject tempObject){
        object.remove(tempObject);
    }

    public void setUp(boolean b){
        up = b;
    }

    public void setDown(boolean b){
        down = b;
    }

    public void setLeft(boolean b){
        left = b;
    }

    public void setRight(boolean b){
        right = b;
    }

    public void setTimer(int i){
        timer = i;
    }

    public void setGtimer(int i) {
        gtimer = i;
    }

    public boolean getUp() {
        return up;
    }

    public boolean getDown() {
        return down;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public int lureTimer() {
        return timer;
    }

    public int grenadeTimer() {
        return gtimer;
    }

    public void gExplode(int x, int y, spriteSheet ss) {
        this.ss = ss;
        extimer = 60;
        explode1 = ss.grabImage(256, 0, 64, 64);
        explode2 = ss.grabImage(128, 0, 64, 64);
        explode3 = ss.grabImage(192, 0, 64, 64);
        exx = x;
        exy = y;
    }

}
