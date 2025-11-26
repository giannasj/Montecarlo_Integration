public class myAnimation {
    private Function function;
    private int numOfSamples;
    public int samplesGenerated;
    public int netSampleCount;

    public myAnimation(Function f, int n) {
        this.function=f;
        this.numOfSamples=n;
        this.samplesGenerated=0;
    }

    public myPoint nextStep() {
        myPoint p = new myPoint(function);
        p.generate();
        samplesGenerated++;
        if (p.isWithin()){
            netSampleCount+=(p.isPositive())?1:-1;
        }
        return p;
    }

    public boolean isDone() {
        return samplesGenerated>=numOfSamples;
    }
}
