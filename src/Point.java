package class_files;
import java.lang.Math;
public class Point {
    private Function function;
    private double x;
    private double y;
    private boolean isWithin;

    public Point(Function f) {
        this.function=f;
    }

    public void generate() {
        //generates random x and y values within the 'box' of integration
        this.x = Math.random()*(function.getUpper()-function.getLower())+function.getLower();
        this.y = Math.random()*(function.getMax()-function.getMin())+function.getMin();

        //calculates the value of the function at the generated x-value
        double trueValue = function.evaluateAt(x);

        //if the function is positive at the generated x-value, the point must be positive and below the function to lie within the integral
        if (trueValue>0) this.isWithin = y<trueValue && y>0;
        //if function is negative at x-value, point must be negative and above the function to lie within the region
        if (trueValue<0) this.isWithin = y>trueValue && y<0;
    }

    public boolean isWithin() {
        return this.isWithin;
    }

    public boolean isPositive() {
        return this.y>0;
    }

    public boolean isNegative() {
        return this.y<0;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
