public interface IGate {

    /**
     * Goes through and does the arithmetic of the Gate
     * @param u0 unit0 involved
     * @param u1 unit1 involved
     * @return returns the higher level unit, and creates
     */
    public Unit forward(Unit u0,Unit u1);

    public void backward();

}
