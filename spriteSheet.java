import java.awt.image.BufferedImage;

public class spriteSheet {

    private BufferedImage image;

    public spriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return image.getSubimage(col, row, width, height);
    }
}
