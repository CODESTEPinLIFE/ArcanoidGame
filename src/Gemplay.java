import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gemplay extends JPanel implements KeyListener, ActionListener {
    private boolean GameFlag = false;
    private int score = 0;
    Random random;
    private MapGeneration map;
    Timer timer;
    private int ScoreBlock = 20 ;
    private  int delay = 10;
    private int PlayerX = 310;
    private int BallX = 120;
    private int BallY = 350;
    private int dirBallX = -1;
    private int dirBallY = -2;


    public Gemplay(){
    map = new MapGeneration(3,7);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
   timer = new Timer(delay,this);
   timer.start();

}
public void paint(Graphics graphics){
    graphics.setColor(Color.BLACK);
    graphics.fillRect(1,1,692,592);



    map.draw((Graphics2D)graphics);

    graphics.setColor(Color.YELLOW);
    graphics.fillRect(0,0,3,592);
    graphics.fillRect(0,0,692,3);
    graphics.fillRect(691,0,3,592);

    graphics.setColor(Color.GREEN);
    graphics.fillRect(PlayerX,550,100,8);

    graphics.setColor(Color.white);
    graphics.setFont(new Font("serif",Font.BOLD,30));
    graphics.drawString("Score :"+score,530,30);

    graphics.setColor(Color.RED);
    graphics.fillOval(BallX,BallY,20,20);
if(ScoreBlock<=0){
    graphics.setColor(Color.green);
    graphics.setFont(new Font("serif",Font.BOLD,30));
    graphics.drawString("Win",190,300);
    dirBallX = 0;
    dirBallY = 0;

}
    if(BallY>570){
        GameFlag = false;
        dirBallX = 0;
        dirBallY = 0;
        graphics.setColor(Color.red);
        graphics.setFont(new Font("serif",Font.BOLD,30));
        graphics.drawString("GameOver",190,300);
    }

    graphics.dispose();
}
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (PlayerX >= 600) {
                PlayerX = 600;
            }
             else{
                    moveRight();
             }
            }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (PlayerX < 15) {
                PlayerX = 15;
            }
         else {
            moveLeft();
        }
    }
    }

    public void moveLeft() {
        GameFlag = true;
        PlayerX-=10;
    }

    public void moveRight() {
    GameFlag = true;
    PlayerX+=10;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
timer.start();
if(GameFlag){
    if(new Rectangle(BallX,BallY,20,20).intersects(new Rectangle(PlayerX,550,100,8))){
        dirBallY =-dirBallY;
    }
    A:for (int i = 0;i < map.map.length;i++){
        for (int j = 0;j < map.map[0].length; j++) {
            if(map.map[i][j]>0) {
                int BrickX = j * map.width+80;
                int BrickY = i * map.heidth+50;
                int Brickwidth = map.width;
                int BrickHeidth = map.heidth;
                Rectangle rect = new Rectangle(BrickX, BrickY, Brickwidth, BrickHeidth);
                Rectangle ball = new Rectangle(BallX, BallY, 20, 20);
                Rectangle brickRect = rect;
                if (ball.intersects(brickRect)) {
                    map.setbrickvalue(0, i, j);

                    ScoreBlock--;
                    score += 1;
                    if (BallX + 19 <= brickRect.x || BallX + 1 >= brickRect.x + brickRect.width) {
                        dirBallX = -dirBallX;
                    } else {
                        dirBallY = -dirBallY;
                    }
                    break A;
                }
            }
        }

    }
    BallX+= dirBallX;
    BallY+= dirBallY;
    if(BallX<0){
        dirBallX = -dirBallX;
    }
    if(BallY<0){
        dirBallY = -dirBallY;
    }
    if(BallX > 670){
        dirBallX = -dirBallX;
    }

}
repaint();
    }
}
