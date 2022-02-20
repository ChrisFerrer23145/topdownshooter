public class camera {
    private float x, y;

    public camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(gameObject object) {
        x += ((object.getx() - x) - 952/2)  * 0.05f;
        y += ((object.gety() - y) - 499/2)  * 0.05f;

        if (x <= 0) x = 0;
        if (x >= 1064) x = 1064;
        if (y <= 0) y = 0;
        if (y >= 1524) y = 1524;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
