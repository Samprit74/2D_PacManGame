import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PacMan extends JPanel implements ActionListener, KeyListener {

    class Block {
        int x;
        int y;
        int width;
        int height;
        Image image;
        int startx;
        int starty;
        char direction = 'U';// U,D,L,R
        int velocityX = 0;
        int velocityY = 0;

        Block(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.startx = x;
            this.starty = y;

        }

        void updateDirection(char direction) {
            char previousDirection = this.direction;
            this.direction = direction;
            updateVelocity();
            this.x += this.velocityX;
            this.y += this.velocityY;
            for (Block wall : walls) {
                if (collition(this, wall)) {
                    this.x -= this.velocityX;
                    this.y -= this.velocityY;
                    this.direction = previousDirection;
                    updateVelocity();
                }
            }
        }

        void updateVelocity() {
            if (this.direction == 'U') {
                this.velocityX = 0;
                this.velocityY = -tileSize / 4;
            } else if (this.direction == 'D') {
                this.velocityX = 0;
                this.velocityY = tileSize / 4;
            } else if (this.direction == 'L') {
                this.velocityX = -tileSize / 4;
                this.velocityY = 0;
            } else if (this.direction == 'R') {
                this.velocityX = tileSize / 4;
                this.velocityY = 0;
            }
        }

        void reset(){
            this.x = this.startx;
            this.y=this.starty;

        }
    }

    private int rowCount = 21;
    private int colCount = 19;
    private int tileSize = 32;
    private int boardWidth = colCount * tileSize;
    private int boardHeight = rowCount * tileSize;

    private Image wallImage;
    private Image blueGhostImage;
    private Image OrangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    // X = wall, O = skip, P = pac man, ' ' = food
    // Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    HashSet<Block> walls;
    HashSet<Block> foods;
    HashSet<Block> ghosts;
    Block pacman;

    Timer gameLoop;
    char[] directions = { 'U', 'D', 'L', 'R' };
    Random random = new Random();
    int score = 0;
    int lives = 3;
    boolean gameOver = false;

    PacMan() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        // load images
        wallImage = new ImageIcon(getClass().getResource("/resources/wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("/resources/blueGhost.png")).getImage();
        OrangeGhostImage = new ImageIcon(getClass().getResource("/resources/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("/resources/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("/resources/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("/resources/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("/resources/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("/resources/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("/resources/pacmanRight.png")).getImage();
        loadMap();
        for (Block ghost : ghosts) {
            char newDirections = directions[random.nextInt(4)];
            ghost.updateDirection(newDirections);
        }
        // how long it takes to start timer,milliseconds gone, between, frames
        gameLoop = new Timer(50, this);// 20fps (1000/50)
        gameLoop.start();
        // System.out.println(walls.size());
        // System.out.println(foods.size());
        // System.out.print(ghosts.size());
    }

    public void loadMap() {
        walls = new HashSet<Block>();
        foods = new HashSet<Block>();
        ghosts = new HashSet<Block>();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                String row = tileMap[r];
                char tilemapChar = row.charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;
                if (tilemapChar == 'X') {// block wall
                    Block wall = new Block(wallImage, x, y, tileSize, tileSize);
                    walls.add(wall);
                } else if (tilemapChar == 'b') {// blue ghost
                    Block ghost = new Block(blueGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                } else if (tilemapChar == 'o') {// orange ghost
                    Block ghost = new Block(OrangeGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                } else if (tilemapChar == 'r') {// red ghost
                    Block ghost = new Block(redGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                } else if (tilemapChar == 'p') {// pink ghost
                    Block ghost = new Block(pinkGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                } else if (tilemapChar == 'P') {// pac-man
                    pacman = new Block(pacmanRightImage, x, y, tileSize, tileSize);

                } else if (tilemapChar == ' ') {// food
                    Block food = new Block(null, x + 14, y + 14, 4, 4);
                    foods.add(food);
                }

            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        for (Block ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        for (Block wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }
        g.setColor(Color.YELLOW);
        for (Block food : foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }
        //score
        g.setFont(new Font("Arial", Font.PLAIN,18));
        if(gameOver){
            g.drawString("Game Over :  "+ String.valueOf(score),tileSize/2,tileSize/2);
        }else{
            g.drawString("x"+ String.valueOf(lives)+"Score : "+ String.valueOf(score),tileSize/2,tileSize/2);
        }

    }

    public void move() {
        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;
        // check wall collition
        for (Block wall : walls) {
            if (collition(pacman, wall)) {
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }
        }
        // check ghost collections
        for (Block ghost : ghosts) {
            if(collition(ghost, pacman)){
                lives -=1;
                if(lives == 0){
                    gameOver = true;
                }
                resetPositions();
            }
            if (ghost.y == tileSize * 9 && ghost.direction != 'U' && ghost.direction != 'D') {
                ghost.updateDirection('U');
            }
            ghost.x += ghost.velocityX;
            ghost.y += ghost.velocityY;
            for (Block wall : walls) {
                if (collition(ghost, wall) || ghost.x <= 0 || ghost.x + ghost.width >= boardWidth) {
                    ghost.x -= ghost.velocityX;
                    ghost.y -= ghost.velocityY;
                    // char verticalDir = random.nextBoolean() ? 'U' : 'D';
                    // ghost.updateDirection(verticalDir);
                    char newDirections = directions[random.nextInt(4)];
                    ghost.updateDirection(newDirections);
                }
            }
        }

        //check food collision
        Block foodEaten =null;
        for(Block food : foods){
            if(collition(pacman, food)){
                foodEaten  = food;
                score += 10;
            }
        }
        foods.remove(foodEaten);
        if(foods.isEmpty()){
            loadMap();
            resetPositions();
        }
    }

    public boolean collition(Block a, Block b) {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }
    
    public void resetPositions(){
        pacman.reset();
        pacman.velocityX = 0;
        pacman.velocityY = 0;
        for(Block ghost: ghosts){
            ghost.reset();
            char newDirections = directions[random.nextInt(4)];
            ghost.updateDirection(newDirections);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // called when a key is pressed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //game restart by pressing any key
        if(gameOver){
            loadMap();
            resetPositions();
            lives=3;
            score=0;
            gameOver=false;
            gameLoop.start();
        }
        // called when a key is released
        System.out.println("keyevent" + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.updateDirection('U');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.updateDirection('D');
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.updateDirection('L');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.updateDirection('R');
        }

        if (pacman.direction == 'U') {
            pacman.image = pacmanUpImage;
        } else if (pacman.direction == 'D') {
            pacman.image = pacmanDownImage;
        } else if (pacman.direction == 'L') {
            pacman.image = pacmanLeftImage;
        } else if (pacman.direction == 'R') {
            pacman.image = pacmanRightImage;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // called when a key is typed (pressed + released quickly)
    }

}
