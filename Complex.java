public class Complex {
    double[] num = {0,0};
    public Complex(double a, double b){
        num[0] = a;
        num[1] = b;
    }
    public void setReal(double a){
        num[0] = a;
    }
    public void setImaginary(double b){
        num[1] = b;
    }
    public double getReal(){
        return num[0];
    }
    public double getImaginary(){
        return num[1];
    }
    public static Complex add(Complex a, Complex b){
        Complex result = new Complex(a.getReal()+b.getReal(), a.getImaginary() + b.getImaginary());
        return result;
    }
    public static Complex euler(double r, double theta){
        Complex result = new Complex(r*Math.cos(theta), r*Math.sin(theta));
        return result;
    }
    public static Complex multiply(Complex a, Complex b){
        Complex result = new Complex(a.getReal()*b.getReal() - a.getImaginary()*b.getImaginary(), a.getReal()*b.getImaginary() + a.getImaginary()*b.getReal());
        return result;
    }
    public double amp(){
        double result = Math.sqrt(num[0]*num[0] + num[1]*num[1]);
        return result;
    }
    public double angle(){
        if (num[0] == 0){
            if (num[1]>0) return Math.PI/2;
            else if (num[1]<0) return -Math.PI/2;
            else return 0;
        }
        else {
        double result = Math.atan2(num[1],num[0]);
        return result;
        }
    }
} 
