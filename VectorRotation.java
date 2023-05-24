import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
//import Package.eval;
public class VectorRotation extends JPanel {
    public static int height, width;    
    public double centerX, centerY, radius;
    public double angle;
    public double[] vectorX, vectorY;
    public Timer timer;
    public  BufferedImage buffer;
    public int prevTraceX;
    public int prevTraceY;
    double dt = Math.PI*2/(Points.n);
    public VectorRotation() {

        
        //initialize
        centerX = 400;
        centerY = 400;
        buffer = new BufferedImage(2 * (int) centerX,2 * (int)centerY, BufferedImage.TYPE_INT_ARGB);
        radius = eval.vectors[0][0];
        angle = 0;
        vectorX = new double[Points.n];
        vectorY = new double[Points.n];
        vectorX[0] = centerX + radius*Math.cos(eval.vectors[0][1]);
        vectorY[0] = centerY - radius*Math.sin(eval.vectors[0][1]);
        //finding position of the first vector
        prevTraceX = (int) vectorX[0];
        prevTraceY = (int) vectorY[0];
        for (int i =1; i < Points.n ; i++){
            prevTraceX += (int)(eval.vectors[i][0] * Math.cos(eval.vectors[i][1]));
            prevTraceY -= (int)(eval.vectors[i][0] * Math.sin(eval.vectors[i][1]));
        }
        
        // Create a timer to update the angle of rotation
        
        //double dt = 0.04;
        System.out.println("speed " + dt);
        timer = new Timer(50, e -> {
            angle += dt;
            //if (angle >= Math.PI*2){
            //    angle = 0;
            //}
            updateVector();
        });
        timer.start();
    }
    
    private void updateVector() {
        
        for (int i = 1; i < Points.n; i++){
            vectorX[i] = vectorX[i-1] + eval.vectors[i][0] * Math.cos((i)* angle + eval.vectors[i][1]);
            vectorY[i] = vectorY[i-1] +(- eval.vectors[i][0] * Math.sin((i)* angle + eval.vectors[i][1]));
            //System.out.println(i + " Vector X " + vectorX[i] + " VectorY " + vectorY[i]);
            //System.out.println("Amp and phase " + eval.vectors[i][0] + eval.vectors[i][1]);
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //eval.evaluate(); 
        //g.setColor(Color.RED);//drawing samples
        //for (int i =0; i < Points.n; i++){
        //    g.drawOval((int)Points.points[i].getX()+400,400-(int) Points.points[i].getY(),2,2);
        //}
        // Draw the circle
        g.setColor(Color.decode("#7f95a3"));
        g.drawOval((int) (centerX - radius) , (int) (centerY - radius), (int) radius * 2, (int) radius * 2);
        
        // Draw the vector
        g.setColor(Color.decode("#802237"));
        g.drawLine((int)centerX, (int)centerY, (int)vectorX[0], (int)vectorY[0]);
        
        for (int i =1; i < Points.n; i++){
            g.setColor(Color.decode("#802237"));
            g.drawLine((int) vectorX[i-1], (int) vectorY[i-1], (int) (vectorX[i]), (int) (vectorY[i]));
            g.setColor(Color.decode("#7f95a3"));
            g.drawOval((int) (vectorX[i-1] - eval.vectors[i][0]) , (int) (vectorY[i-1] - eval.vectors[i][0]), (int) eval.vectors[i][0] * 2, (int) eval.vectors[i][0] * 2);
        }
        //tracing(g2);
        
        // Draw the buffer image to the JPanel
        Graphics2D g2 = buffer.createGraphics();

        if (angle < (2 * Math.PI - dt)){ //optimizing to stop adding new components after full rotation
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(4));
        
            // Draw a line from the previous position of the trace to the current position
            g2.drawLine(prevTraceX, prevTraceY, (int) vectorX[Points.n-1], (int) vectorY[Points.n-1]);
        
            // Remember the current position as the previous position for the next update
            prevTraceX = (int) vectorX[Points.n-1];
            prevTraceY = (int) vectorY[Points.n-1];
        }
        g.drawImage(buffer, 0, 0, null); // Draw the buffer image onto itself
        g2.dispose();
    }
/* 
    protected void tracing(Graphics2D g2) {
        //Graphics2D g2d = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(2));
    
        // Draw a line from the previous position of the trace to the current position
        g2.drawLine(prevTraceX, prevTraceY, (int) vectorX[Points.n-1], (int) vectorY[Points.n-1]);
    
        // Remember the current position as the previous position for the next update
        prevTraceX = (int) vectorX[Points.n-1];
        prevTraceY = (int) vectorY[Points.n-1];
    }
*/    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vector Rotation");
        eval.evaluate();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new VectorRotation());
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800,800);
        frame.setVisible(true);
        height = frame.getHeight();
        width = frame.getWidth();
    }
}
