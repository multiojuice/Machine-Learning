public class AddGate implements IGate {

    private Unit u0;
    private Unit u1;
    private Unit topUnit;
    /**
     * Adds the unit values
     * @param u0 unit0 involved
     * @param u1 unit1 involved
     * @return
     */
    @Override
    public Unit forward(Unit u0, Unit u1) {

        this.u0 = u0;
        this.u1 = u1;
        topUnit = new Unit(u0.getValue()+u1.getValue(),0.0);

        return topUnit;
    }

    @Override
    public void backward() {
        u0.setGradient(1.0*topUnit.getGradient());
        u1.setGradient(1.0*topUnit.getGradient());

    }
}
