public class Unit {

    private double gradient;
    private double value;


    public Unit(double value,  double gradient) {
        this.gradient = gradient;
        this.value = value;
    }

    public double getGradient() {
        return gradient;
    }

    public void setGradient(double gradient) {
        this.gradient += gradient;
    }

    public void setValue(double value) {
        this.value += value;
    }

    public double getValue() {
        return value;

    }
}
