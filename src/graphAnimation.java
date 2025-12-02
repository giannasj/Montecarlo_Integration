package class_files;
import Main;
public class graphAnimation {
    private int currentCount;
    private int insideCount;
    private int outsideCount;
    private int netInsideCount;

    public graphAnimation() {
        this.insideCount=0;
        this.outsideCount=0;
        this.netInsideCount=0;
        this.currentCount=0;
    }

    public Point nextStep() {
        Point p = new Point(Main.function);
        p.generate(); //creates a new random point
        currentCount++; //increments the number of points generated
        if (p.isWithin()){ //tests if it lies within the region of integration.
            insideCount++;
            if(p.isPositive()) netInsideCount++; //if the region is positive, add to the net count.
            if(p.isNegative()) netInsideCount--; //if the region is negative, subtract from the net count
        } else {outsideCount++;}
        return p;
    }

    public int getInsideCount() {
        return this.insideCount;
    }

    public int getOutsideCount() {
        return this.outsideCount;
    }

    public int getNetInsideCount() {
        return this.netInsideCount;
    }

    public int getCount() {
        return this.currentCount;
    }
    
}