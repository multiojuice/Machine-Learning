public class temp {

    public static void main(String[] args) {
        // write your code here
        double[][] data = new double[6][2];
        data[0][0] = 1.2;
        data[0][1] = 0.7;
        data[1][0] = -0.3;
        data[1][1] = -0.5;
        data[2][0] = 3.0;
        data[2][1] = 0.1;
        data[3][0] = -0.1;
        data[3][1] = -1.0;
        data[4][0] = -1.0;
        data[4][1] = 1.1;
        data[5][0] = 2.1;
        data[5][1] = -3.0;
        // Initiate variables
        double[] labels = new double[6];
        labels[0] = 1;
        labels[1] = -1;
        labels[2] = 1;
        labels[3] = -1;
        labels[4] = -1;
        labels[5] = 1;

        double a = 1.0;
        double b = -2.0;
        double c = -1.0;

        for(int iter = 0; iter < 700; iter++) {
            int i = (int)Math.floor(Math.random()*data.length);
            double x = data[i][0];
            double y = data[i][1];
            double label =  labels[i];

            double score = a*x + b*y + c;
            double pull = 0.0;
            if(label == 1 && score < 1) pull = 1;
            if(label == -1 && score > -1) pull = -1;
            if(iter%25 == 0) System.out.println(pull);
            // compute gradient and update parameters
            double step_size = 0.01;
            a += step_size * (x * pull - a); // -a is from the regularization
            b += step_size * (y * pull - b); // -b is from the regularization
            c += step_size * (1 * pull);
        }
    }
}
