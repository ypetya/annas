package annas.math;


public class Complex{

	private double r;

	private double i;
    
    /**
        Constructs the complex number z = u + i*v
        @param u Real part
        @param v Imaginary part
    */
    public Complex(double u,double v) {
        r=u;
        i=v;
    }
    
    /**
        Real part of this Complex number 
        (the x-coordinate in rectangular coordinates).
        @return Re[z] where z is this Complex number.
    */
    public double real() {
        return r;
    }
    
    /**
        Imaginary part of this Complex number 
        (the y-coordinate in rectangular coordinates).
        @return Im[z] where z is this Complex number.
    */
    public double imag() {
        return i;
    }
    
    /**
        Modulus of this Complex number
        (the distance from the origin in polar coordinates).
        @return |z| where z is this Complex number.
    */
    public double mod() {
        if (r!=0 || i!=0) {
            return Math.sqrt(r*r+i*i);
        } else {
            return 0d;
        }
    }
    
    /**
        Argument of this Complex number 
        (the angle in radians with the x-axis in polar coordinates).
        @return arg(z) where z is this Complex number.
    */
    public double arg() {
        return Math.atan2(i,r);
    }
    
    /**
        Complex conjugate of this Complex number
        (the conjugate of x+i*y is x-i*y).
        @return z-bar where z is this Complex number.
    */
    public Complex conj() {
        return new Complex(r,-i);
    }
    
    /**
        Addition of Complex numbers (doesn't change this Complex number).
        <br>(x+i*y) + (s+i*t) = (x+s)+i*(y+t).
        @param w is the number to add.
        @return z+w where z is this Complex number.
    */
    public Complex plus(Complex w) {
        return new Complex(r+w.real(),i+w.imag());
    }
    
    /**
        Subtraction of Complex numbers (doesn't change this Complex number).
        <br>(x+i*y) - (s+i*t) = (x-s)+i*(y-t).
        @param w is the number to subtract.
        @return z-w where z is this Complex number.
    */
    public Complex minus(Complex w) {
        return new Complex(r-w.real(),i-w.imag());
    }
    
    /**
        Complex multiplication (doesn't change this Complex number).
        @param w is the number to multiply by.
        @return z*w where z is this Complex number.
    */
    public Complex times(Complex w) {
        return new Complex(r*w.real()-i*w.imag(),r*w.imag()+i*w.real());
    }
    
    /**
        Division of Complex numbers (doesn't change this Complex number).
        <br>(x+i*y)/(s+i*t) = ((x*s+y*t) + i*(y*s-y*t)) / (s^2+t^2)
        @param w is the number to divide by
        @return new Complex number z/w where z is this Complex number  
    */
    public Complex div(Complex w) {
		double den = w.real() * w.real() + w.imag() * w.imag();
        return new Complex((r*w.real()+i*w.imag())/den,(i*w.real()-r*w.imag())/den);
    }
    
    /**
        Complex exponential (doesn't change this Complex number).
        @return exp(z) where z is this Complex number.
    */
    public Complex exp() {
        return new Complex(Math.exp(r)*Math.cos(i),Math.exp(r)*Math.sin(i));
    }
    
    /**
        Principal branch of the Complex logarithm of this Complex number.
        (doesn't change this Complex number).
        The principal branch is the branch with -pi < arg <= pi.
        @return log(z) where z is this Complex number.
    */
    public Complex log() {
        return new Complex(Math.log(this.mod()),this.arg());
    }
    
    /**
        Complex square root (doesn't change this complex number).
        Computes the principal branch of the square root, which 
        is the value with 0 <= arg < pi.
        @return sqrt(z) where z is this Complex number.
    */
    public Complex sqrt() {
        double r=Math.sqrt(this.mod());
        double theta=this.arg()/2;
        return new Complex(r*Math.cos(theta),r*Math.sin(theta));
    }
    
    // Real cosh function (used to compute complex trig functions)
    private double cosh(double theta) {
        return (Math.exp(theta)+Math.exp(-theta))/2;
    }
    
    // Real sinh function (used to compute complex trig functions)
    private double sinh(double theta) {
        return (Math.exp(theta)-Math.exp(-theta))/2;
    }
    
    /**
        Sine of this Complex number (doesn't change this Complex number).
        <br>sin(z) = (exp(i*z)-exp(-i*z))/(2*i).
        @return sin(z) where z is this Complex number.
    */
    public Complex sin() {
        return new Complex(cosh(i)*Math.sin(r),sinh(i)*Math.cos(r));
    }
    
    /**
        Cosine of this Complex number (doesn't change this Complex number).
        <br>cos(z) = (exp(i*z)+exp(-i*z))/ 2.
        @return cos(z) where z is this Complex number.
    */
    public Complex cos() {
        return new Complex(cosh(i)*Math.cos(r),-sinh(i)*Math.sin(r));
    }
    
    /**
        Hyperbolic sine of this Complex number 
        (doesn't change this Complex number).
        <br>sinh(z) = (exp(z)-exp(-z))/2.
        @return sinh(z) where z is this Complex number.
    */
    public Complex sinh() {
        return new Complex(sinh(r)*Math.cos(i),cosh(r)*Math.sin(i));
    }
    
    /**
        Hyperbolic cosine of this Complex number 
        (doesn't change this Complex number).
        <br>cosh(z) = (exp(z) + exp(-z)) / 2.
        @return cosh(z) where z is this Complex number.
    */
    public Complex cosh() {
        return new Complex(cosh(r)*Math.cos(i),sinh(r)*Math.sin(i));
    }
    
    /**
        Tangent of this Complex number (doesn't change this Complex number).
        <br>tan(z) = sin(z)/cos(z).
        @return tan(z) where z is this Complex number.
    */
    public Complex tan() {
        return (this.sin()).div(this.cos());
    }
    
    /**
        Negative of this complex number (chs stands for change sign). 
        This produces a new Complex number and doesn't change 
        this Complex number.
        <br>-(x+i*y) = -x-i*y.
        @return -z where z is this Complex number.
    */
    public Complex chs() {
        return new Complex(-r,-i);
    }
    
    /**
        String representation of this Complex number.
        @return x+i*y, x-i*y, x, or i*y as appropriate.
    */
    public String toString() {
        if (r!=0 && i>0) {
            return r+"+i*"+i;
        }
        if (r!=0 && i<0) {
            return r+"-i*"+(-i);
        }
        if (i==0) {
            return String.valueOf(r);
        }
        if (r==0) {
            return "i*"+i;
        }
        // shouldn't get here (unless Inf or NaN)
        return r+"+i*"+i;
        
    }       
}

