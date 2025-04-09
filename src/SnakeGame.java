package src;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;



public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;


    //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody = new ArrayList<>(); 

    //Food
    Tile food;
    Random random;

    // Game logic
    Timer gameloop;
    int velocityX;
    int velocityY;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    
       setPreferredSize(new Dimension(boardWidth, boardHeight));
       setBackground(Color.black);
       addKeyListener(this);
         setFocusable(true);

       snakeHead = new Tile(5, 5);
       snakeBody = new ArrayList<Tile>();

       food = new Tile (10, 10);
       random = new Random();
       placeFood();

       velocityX = 0;
       velocityY = 0;

        gameloop = new Timer(100, this);
        gameloop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //Grid
        for (int i = 0; i < boardWidth/tileSize; i++){
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        }
 
        //Food

        g.setColor(Color.yellow);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //Snake
        g.setColor(Color.MAGENTA);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void placeFood(){
       food.x = random.nextInt(boardWidth/tileSize); // 600 : 25 = 24
       food.y = random.nextInt(boardHeight/tileSize);
    }

    public void move(){
        //Snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
      repaint();
    }

 @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // don't need to implement this method, but it is required by the interface
    @Override
    public void keyTyped(KeyEvent e) { }  

    @Override
    public void keyReleased(KeyEvent e) {}
        
    
}