import java.awt.image.BufferedImage;

public class spriteSheet {

    private BufferedImage image;

    public spriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }
}
