import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Points {

    public static Point2D[] points;
    public static int n;

    static {
        ArrayList<Point2D> pointList = new ArrayList<Point2D>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("samples.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                pointList.add(new Point2D.Double(x, y));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        n = pointList.size();
        points = pointList.toArray(new Point2D[n]);
    }
}