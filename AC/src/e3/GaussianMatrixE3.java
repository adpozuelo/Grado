package e3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * GaussianMatrix class of naive bayes classifier. Class to create a gaussian
 * matrix from a train set. UOC AC PRA - Antonio Díaz Pozuelo
 *
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
public class GaussianMatrixE3 {

    private ArrayList<SampleE3> trainSet; // Final train set.
    private ArrayList<String> header; // Header of dataset.
    private ArrayList<String> classes; // Dataset's classes.
    private ArrayList<ArrayList<Double>> gaussianMatrix; // Final gaussian matrix from train set
    private ArrayList<Double> classProbability; // Class probability.

    /**
     * Constructor
     *
     * @param trainSet
     * @param header
     * @param classes
     */
    public GaussianMatrixE3(ArrayList<SampleE3> trainSet, ArrayList<String> header, ArrayList<String> classes) {
        this.trainSet = trainSet;
        this.header = header;
        this.classes = classes;
        this.gaussianMatrix = new ArrayList<>();
        this.classProbability = new ArrayList<>();
        createGaussianMatrix();
    }

    /**
     * Print gaussian matrix and class probabilty.
     */
    public void printGaussianMatrix() {
        System.out.println("\nGaussian Matrix:");
        System.out.print("Class ");
        for (int i = 1; i < this.header.size(); i++) {
            System.out.print(this.header.get(i) + "(avg) " + this.header.get(i) + "(var) ");
        }
        System.out.println();
        for (int x = 0; x < this.gaussianMatrix.size(); x++) {
            System.out.print(this.classes.get(x) + " ");
            for (int y = 0; y < this.gaussianMatrix.get(x).size(); y++) {
                System.out.print(this.gaussianMatrix.get(x).get(y) + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < this.classProbability.size(); i++) {
            System.out.println("Probability class " + this.classes.get(i) + ": " + this.classProbability.get(i));
        }
    }

    /**
     * Create guassian matrix from train set and class probability.
     */
    private void createGaussianMatrix() {
        for (int x = 0; x < this.classes.size(); x++) { // For each class we calculate guassian row
            // Data initialization
            ArrayList<Double> gaussianFile = new ArrayList<>();
            String kind = this.classes.get(x);
            Double[] avg = new Double[this.header.size() - 1];
            for (int i = 0; i < avg.length; i++) {
                avg[i] = new Double(0);
            }
            Double[] var = new Double[this.header.size() - 1];
            for (int i = 0; i < var.length; i++) {
                var[i] = new Double(0);
            }
            Iterator<SampleE3> ot = this.trainSet.iterator();
            int count = 0; // counter of number of samples by class
            while (ot.hasNext()) { // Iterate all trainset
                SampleE3 sample = ot.next(); // Get a sample
                if (sample.getKind().equals(kind)) { // If class's sample is equal to finded class
                    for (int i = 0; i < sample.getAttributes().size(); i++) { // For each atribute of the class
                        avg[i] += Double.parseDouble(sample.getAttributes().get(i)); // Acumulate value to average
                    }
                    count++; // counter updated due to class sample is equal to finded class
                }
            }
            if (count < 2) { // If train set for one class is too small stop runtime.
                System.out.println("\nERROR, train set for one class is too small. Try to increase train percentage");
                System.out.println();
                System.exit(0);
            }
            // Calculate the average of all atributes after iterate by all samples by finded class
            for (int i = 0; i < avg.length; i++) {
                avg[i] /= count;
            }
            Iterator<SampleE3> st = this.trainSet.iterator(); // The same operation above but with the variance.
            while (st.hasNext()) {
                SampleE3 sample = st.next();
                if (sample.getKind().equals(kind)) {
                    for (int i = 0; i < sample.getAttributes().size(); i++) {
                        var[i] += Math.pow(Double.parseDouble(sample.getAttributes().get(i)) - avg[i], 2); // Sumatory operation (X-Xavg)^2
                    }
                }
            }
            // Calculate the denominator of the variance calculus per atribute by finded class
            for (int i = 0; i < var.length; i++) {
                var[i] /= count - 1;
            }
            // Add sandwich atributes to gaussian's matrix file.
            for (int i = 0; i < avg.length; i++) {
                gaussianFile.add(avg[i]);
                gaussianFile.add(var[i]);
            }
            this.gaussianMatrix.add(gaussianFile); // Add final gaussian's matrix row 
            this.classProbability.add((double) count / this.trainSet.size()); // Calculate the probability off class in trainset.
        }
    }

    /**
     * Getters, no setters needed.
     *
     * @return
     */
    public ArrayList<ArrayList<Double>> getGaussianMatrix() {
        return gaussianMatrix;
    }

    public ArrayList<Double> getClassProbability() {
        return classProbability;
    }
}
