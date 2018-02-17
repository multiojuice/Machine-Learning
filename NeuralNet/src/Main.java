
/**
 * @author Owen Sullivan, Carter Nesbitt
 * Setup for the first push
 *
 */


public class Main {

    public static void main(String[] args) {
	// write your code here
        double[][] data = new double[6][2];
        data[0][0] = 1.2; data[0][1] = 0.7;
        data[1][0] = -0.3; data[1][1] = -0.5;
        data[2][0] = 3.0; data[2][1] = 0.1;
        data[3][0] = -0.1; data[3][1] = -1.0;
        data[4][0] = -1.0; data[4][1] = 1.1;
        data[5][0] = 2.1; data[5][1] = -3.0;
        // Initiate variables
        double[] labels = new double[6];
        labels[0] = 1; labels[1] = -1; labels[2] = 1; labels[3] = -1;
        labels[4] = -1; labels[5] =1;

        SVM svm = new SVM();

        for(int k = 0; k < 1000; k++){

            int i = (int)Math.floor(Math.random()*data.length);
            Unit x = new Unit(data[i][0],0.0);
            Unit y = new Unit(data[i][1],0.0);
            svm.learnFrom(x,y,labels[i]);

            if(k%100 == 0){
                System.out.println("Training state at " + k + " : "+ trainingAccuracy(data,labels,svm));
            }
        }
    }

    public static double trainingAccuracy(double[][] data, double[] labels,SVM svm){
        double correct = 0;

        for(int i = 0;i<data.length;i++){

            Unit x = new Unit(data[i][0],0.0);
            Unit y = new Unit(data[i][1],0.0);

            double predicted_label;
            if(svm.forward(x,y).getValue() > 0){
                predicted_label = 1.0;
            } else {
                predicted_label = -1.0;
            }

            if(predicted_label == labels[i]) correct++;

        }
        return correct/(double)data.length;
    }
}
