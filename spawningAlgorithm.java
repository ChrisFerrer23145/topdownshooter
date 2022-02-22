public class spawningAlgorithm {

    handler handler;
    camera camera;
    spriteSheet ss;
    character character;
    private int timer = 0;
    private int x;
    private int y;

    public spawningAlgorithm (handler handler, camera camera, spriteSheet ss, character character) {
        this.handler = handler;
        this.camera = camera;
        this.ss = ss;
        this.character = (character) character;

    }

    public void tick() {
        try {
            if (timer % 120 == 0) {
                x = character.getx();
                y = character.gety();
                int randx = 0;
                int randy = 0;
                
                randx = (int) (Math.random() * 1064);
                if (randx > x && randx < x + 700 && (x + 700 < 1064)) randx = x + 700; 
                else if (randx > x + 700) {} 
                else if (x + 700 > 1064) randx = x - 700;
                else if (randx < x && randx > x - 700 && x - 700 > 0) randx = x - 700;
                else if (randx < x - 700) {} 
                else if (x - 700 < 0) randx = x + 700;

                if (randx < 0) randx = x + 700;

                randy = (int) (Math.random() * 1524);
                //System.out.println(randx + " " + randy + " " + (randx < 0) + " " + (randy < 0) + " " + (randx > 1064) + " " + (randy > 1524));
                
                handler.addObject(new enemy(randx, randy, 100, id.Enemy, handler, ss));
            }

            timer++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
