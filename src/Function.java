public class Function {
    private double lowerBound;
    private double upperBound;
    private double maxValue;
    private double minValue;

    public Function(double lowerBound, double upperBound) {
        this.lowerBound=lowerBound;
        this.upperBound=upperBound;
        this.maxValue=0;
        this.minValue=0;
        for (double i=lowerBound;i<=upperBound;i+=0.001) {
            double trueValue = evaluateAt(i);
            if (trueValue>this.maxValue) this.maxValue = trueValue;
            if (trueValue<this.minValue) this.minValue = trueValue;
        }
    }

    public double evaluateAt(double x) {
        return x*x*x + 3*x*x + x - 1; //function definition
    }

    public double getLower() {
        return this.lowerBound;
    }

    public double getUpper() {
        return this.upperBound;
    }

    public double getMax() {
        return this.maxValue;
    }

    public double getMin() {
        return this.minValue;
    }

    public double totalArea() {
        return (getUpper()-getLower())*(getMax()-getMin());
    }
}