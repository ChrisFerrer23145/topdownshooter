import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class gameObject {
    protected int posx;
    protected int posy;
    protected int health;
    protected float velx = 0;
    protected float vely = 0;
    public int eneCnt = 0;
    protected id id;
    protected String wep;
    protected Object[][] wepList;
    handler handler;
    protected spriteSheet ss;  

    public gameObject(handler handler){
        this.handler = handler;
    }
    
    public gameObject(int posx, int posy, int health, id id, spriteSheet ss) {
        this.posx = posx;
        this.posy = posy;
        this.health = health;
        this.id = id;
        this.ss = ss;
    }

    public void weapon(String wep, Object[][] wepList) {
        this.wep = wep;
        this.wepList = wepList;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public int getx() {
        return posx;
    }

    public int gety() {
        return posy;
    }

    public int getHealth() {
        return health;
    }
    
    public int getVelx() {
        return (int) velx;
    }

    public int getVely() {
        return (int) vely;
    }

    public id getId() {
        return id;
    }

    public String getWep() {
        return wep;
    }

    public Object[][] getWepList() {
        return wepList;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setx(int posx) {
        this.posx = posx;
    }

    public void sety(int posy) {
        this.posy = posy;
    }

    public void setVelx(float velx) {
        this.velx = velx;
    }

    public void setVely(float vely) {
        this.vely = vely;
    }

    public void setId(id id) {
        this.id = id;
    }

    public void setWep(String wep) {
        this.wep = wep;
    }

    public void setWepList(Object[][] wepList) {
        this.wepList = wepList;
    }


}
