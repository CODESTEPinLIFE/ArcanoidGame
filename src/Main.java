import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame obj = new JFrame();
        Gemplay gemplay = new Gemplay();
        obj.setTitle("Arcanoid");
        obj.setBounds(10,10,710,600);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gemplay);
    }
}