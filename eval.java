public class eval {
    public static double[][] vectors= new double[Points.n][2];
    public static void evaluate(){
        double[][] psamples = new double[Points.n][2];
        for (int i = 0; i < Points.n; i++){
            psamples[i][0] = Points.points[i].getX();
            psamples[i][1] = Points.points[i].getY();
        }
        vectors = Dft.dft(psamples);

    }
}
