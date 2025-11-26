import java.lang.Math;
public class myPoint {
    private Function function;
    public double x;
    public double y;
    private boolean isWithin;

    public myPoint(Function f) {
        this.function=f;
    }

    public void generate() {
        this.x = Math.random()*(function.getUpper()-function.getLower())+function.getLower();
        this.y = Math.random()*(function.getMax()-function.getMin())+function.getMin();
        double trueValue = function.evaluateAt(x);
        if (trueValue>0) this.isWithin = y<trueValue && y>0;
        if (trueValue<0) this.isWithin = y>trueValue && y<0;
        if (trueValue==0) this.isWithin = y==0;
    }

    public boolean isWithin() {
        return this.isWithin;
    }

    public boolean isPositive() {
        return this.y>0;
    }

    public double[] toArray(){
        return new double[] {x,y};
    }
}
