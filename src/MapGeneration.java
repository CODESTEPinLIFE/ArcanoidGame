import java.awt.*;

public class MapGeneration {
    public int map[][];
    public int width;
    public int heidth;

    public MapGeneration(int row,int col){
        map = new int[row][col];
        for (int i = 0;i < map.length;i++){
            for (int j = 0;j < map[0].length; j++){
                 map[i][j]=1;
            }
        }
        width = 540/col;
        heidth = 150/row;

    }
     public void draw(Graphics2D graphics){
         for (int i = 0;i < map.length;i++){
             for (int j = 0;j < map[0].length; j++){
                 if(map[i][j]>0){
                     graphics.setColor(Color.white);
                     graphics.fillRect(j*width+80,i*heidth+50,width,heidth);

                     graphics.setStroke(new BasicStroke(3));
                     graphics.setColor(Color.BLACK);
                     graphics.drawRect(j*width+80,i*heidth+50,width,heidth);
                 }
             }
         }
     }
     public void setbrickvalue(int value ,int row,int col){
        map[row][col]=value;
     }
}
