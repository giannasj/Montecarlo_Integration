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
        for (double i=lowerBound;i<=upperBound;i+=0.001) { //calculating max and min values. Defaults to zero if no higher/lower value is found to properly display the integration region.
            double trueValue = evaluateAt(i);
            if (trueValue>this.maxValue) this.maxValue = trueValue;
            if (trueValue<this.minValue) this.minValue = trueValue;
        }
    }

    public double evaluateAt(double x) {
        return x*x-2; //function definition
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

    public double totalArea() { //total area of the 'box'
        return (getUpper()-getLower())*(getMax()-getMin());
    }
}