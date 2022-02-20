import java.awt.Dimension;

import javax.swing.JFrame;

public class window {
    public window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
