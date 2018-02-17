public class SVM {

    private Unit a = new Unit(1.0,0.0);
    private Unit b = new Unit(-2.0,0.0);
    private Unit c = new Unit(-1.0,0.0);
    private Unit unit_out;

    private Circuit circuit = new Circuit();


    public Unit forward(Unit x, Unit y) {

        unit_out = circuit.forward(a,b,c,x,y);
        return unit_out;
    }


    private void backward(double label) {

        a.setGradient(0.0);
        b.setGradient(0.0);
        c.setGradient(0.0);

        double pull = 0.0;

        if(label == 1 && unit_out.getValue() < 1) pull = 1.0;
        if(label == -1 && unit_out.getValue() > -1) pull = -1.0;

        circuit.backward(pull);

        a.setGradient(-a.getValue());
        b.setGradient(-b.getValue());
    }


    private void parameterUpdate(){
        double step = 0.001;
        a.setValue(step*a.getGradient());
        b.setValue(step*b.getGradient());
        c.setValue(step*c.getGradient());
    }


    public void learnFrom(Unit x, Unit y, double label){

        this.forward(x,y);
        this.backward(label);
        this.parameterUpdate();
    }



}
