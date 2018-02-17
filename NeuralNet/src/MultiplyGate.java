public class MultiplyGate implements IGate{


    private Unit u0;
    private Unit u1;
    private Unit topUnit;

    public Unit forward(Unit u0, Unit u1) {
        this.u0 = u0;
        this.u1 = u1;
        topUnit = new Unit(u0.getValue() * u1.getValue(), 0.0);

        return topUnit;
    }

    public void backward(){

        u0.setGradient(u1.getValue()*topUnit.getGradient());
        u1.setGradient(u0.getValue()*topUnit.getGradient());
    }

}
