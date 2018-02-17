

public class Circuit {

    private IGate mulGate0 = new MultiplyGate();
    private IGate mulGate1 = new MultiplyGate();
    private IGate addGate0 = new AddGate();
    private IGate addGate1 = new AddGate();
    private Unit axpbypc;


    public Unit forward(Unit a, Unit b, Unit c, Unit x, Unit y){

        Unit ax = mulGate0.forward(a,x);
        Unit by = mulGate1.forward(b,y);
        Unit axpby = addGate0.forward(ax,by);
        axpbypc = addGate1.forward(axpby,c);
        return axpbypc;
    }

    public void backward(double top_gradient){

        axpbypc.setGradient(top_gradient);
        addGate1.backward();
        addGate0.backward();
        mulGate1.backward();
        mulGate0.backward();
    }


}
