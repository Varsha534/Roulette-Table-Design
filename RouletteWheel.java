import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RouletteWheel extends JPanel
{
    public static void main(String[] args)
    {
        JFrame F = new JFrame();
        F.add(new RouletteWheel());
        F.setSize(600,600);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    int i = 0;
    public void paintComponent (Graphics g) 
    {
        BufferedImage wheel = LoadImage("roulettewheel.png");
        BufferedImage ball = LoadImage("Ball.png");

        AffineTransform wheelTransform = AffineTransform.getTranslateInstance(10,10);
        wheelTransform.rotate(Math.toRadians(i++), wheel.getWidth()/2, wheel.getHeight()/2);
        wheelTransform.scale(1,1);

        AffineTransform ballTransform = AffineTransform.getTranslateInstance(10, 10);
        ballTransform.rotate(Math.toRadians(-i++), wheel.getWidth()/2, wheel.getHeight()/2);
        ballTransform.scale(0.36, 0.36);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(wheel, wheelTransform, null);
        g2d.drawImage(ball, ballTransform, null);
        repaint();

    }

    BufferedImage LoadImage(String FileName)
    {
        BufferedImage image = null;
        try {
        image = ImageIO.read(new File(FileName));
        }
        catch(IOException e){
        }
        return image;
    }

}
