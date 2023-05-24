public class Dft {
    public static double[][] dft(double[][] psamples){
        double[][] result = new double[Points.n][2];
        for (int i = 0; i < Points.n; i++){
            Complex x_i = new Complex(0, 0);
            for (int j = 0; j < Points.n; j++){
                Complex sample = new Complex(psamples[j][0], psamples[j][1]);
                double root = Math.PI*2*i*j/Points.n;
                Complex unity = new Complex(Math.cos(root), -Math.sin(root));
                x_i = Complex.add(x_i, Complex.multiply(sample, unity));
            }
            result[i][0] = x_i.amp()/Points.n; //amplitude
            result[i][1] = x_i.angle(); //phase change
            //frequency is equal to the index number i
            System.out.println(i + " amp: " + result[i][0] + " phase change: " + result[i][1]);
        }
        return result;
    } 
}
