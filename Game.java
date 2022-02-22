import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

class Game extends Canvas implements Runnable {
    private boolean running = false;
    private Thread thread;
    private handler handler;
    private camera camera;
    private spriteSheet ss;
    private spawningAlgorithm algorithm;

    private BufferedImage level = null;
    private projectile projectile;
    private BufferedImage spriteSheet = null;
    private BufferedImage floor = null;

    public static void main(String[] args) throws Exception {
        new Game();
    }

    public Game() {
        new window(1000, 563, "Top Down Shooter", this);
        start();

        handler = new handler();
        camera = new camera(0, 0);
        projectile = new projectile(handler);
        this.addKeyListener(new keyInput(handler, projectile));
        
        imgLoader loader = new imgLoader(); 
        level = loader.loadImage("src/level.png");
        spriteSheet = loader.loadImage("src/spriteSheet.png");
        ss = new spriteSheet(spriteSheet);
        floor = ss.grabImage(32, 30, 32, 32);
        this.addMouseListener(new mouseInput(handler, camera, this, ss));
        
        loadLevel(level);

        character tempCharacter = null;

        for(int i = 0; i < handler.object.size(); i++){
            gameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == id.Character){
                tempCharacter = (character) tempObject;
            }
        }

        algorithm = new spawningAlgorithm(handler, camera, ss, tempCharacter);
        

    }

    private void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                // updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                // updates = 0;
            }
        }
        stop();
    }

    public void tick() {

        for(int i = 0; i < handler.object.size(); i++){
            gameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == id.Character){
                camera.tick(tempObject);
                algorithm.tick();
            }
        }

        handler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(-camera.getX(), -camera.getY());


        for (int xx = 0; xx < 30*72; xx+=32) {
            for (int yy = 0; yy < 30*72; yy+=32) {
                g.drawImage(floor, xx, yy, null);

            }
        }
        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());

        
        int hp = 100;
        for(int i = 0; i < handler.object.size(); i++){
            gameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == id.Character){
                hp = tempObject.getHealth() * 2;
            }
        }
        g.setColor(Color.green);
        g.fillRect(5, 5, hp, 16);
        g.setColor(Color.red);
        g.fillRect(hp, 5, 200-hp, 16);

        g.dispose();
        bs.show();
    }

    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) handler.addObject(new block(xx * 32, yy * 32, Integer.MAX_VALUE, id.Block, ss));
                if (blue == 255) handler.addObject(new character(xx * 32, yy * 32, 100, id.Character, handler, ss));
                if (green == 255) handler.addObject(new enemy(xx * 32, yy * 32, 100, id.Enemy, handler, ss));
            }
        }
    }
}