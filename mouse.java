import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.IOException; // Import the IOException class
import java.io.PrintWriter;

public class mouse extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int n;
    public final static int skip = 2;
    public static Point2D[] samplePoints;

    public static void main(String[] args) {
        mouse app = new mouse();
        app.setVisible(true);
    }

    public mouse() {
        super("Mouse Draw");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;
            private List<Point2D> allpoints = new ArrayList<Point2D>();

            {
                addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        allpoints.add(e.getPoint());
                        repaint();
                    }
                    public void mouseReleased(MouseEvent e) {
                        /*samplePoints = new Point2D[n];
                        System.out.println("The sample points are: ");
                        for (int i =0; i <n; i++){
                            int element = (int) i*allpoints.size()/n;
                            samplePoints[i] = new Point2D.Double(allpoints.get(element).getX()-400, allpoints.get(element).getY()-400);
                            System.out.println(samplePoints[i]);
                        }*/
                        n = allpoints.size();
                        samplePoints = new Point2D[(int) (allpoints.size()-1)/skip + 1];
                        System.out.println("allpoints are " + allpoints.size() + " and " + (int) (allpoints.size()/(skip)+1));
                        System.out.println("The sample points are: ");
                        int count = 0;
                        for (int i =0; i <allpoints.size(); i+= skip){
                            samplePoints[count] = new Point2D.Double(allpoints.get(i).getX()-400, 400 - allpoints.get(i).getY());
                            System.out.println(samplePoints[count].getX() + "," + samplePoints[count].getY());
                            count++;
                        }

                        allpoints.clear();
                    
                        try {
                            PrintWriter writer = new PrintWriter("samples.txt", "UTF-8");
                            for (Point2D point : samplePoints) {
                                writer.println(point.getX() + "," + point.getY());
                            }
                            writer.close();
                            System.out.println("Sample points saved to samples.txt");
                        } catch (IOException ex) {
                            System.out.println("An error occurred while writing to the file.");
                            ex.printStackTrace();
                        }
                    }
                });

                addMouseMotionListener(new MouseAdapter() {
                    public void mouseDragged(MouseEvent e) {
                        System.out.println(e);
                        allpoints.add(e.getPoint());
                        repaint();
                    }
                });
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.GREEN);
                g2d.setStroke(new BasicStroke(4));
                for (int i = 0; i < allpoints.size() - 1; i++) {
                    Point2D p1 = allpoints.get(i);
                    Point2D p2 = allpoints.get(i + 1);
                    g2d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
                }
            }
        };
        setContentPane(panel);
    }
}
