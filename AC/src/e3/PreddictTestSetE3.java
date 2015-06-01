package e3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * PreddictTestSet class of naive bayes classifier. Class to preddict test set
 * using guassian matrix. UOC AC PRA - Antonio Díaz Pozuelo
 *
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
public class PreddictTestSetE3 {

    private ArrayList<ArrayList<Double>> gaussianMatrix; // Final gaussian matrix.
    private ArrayList<Double> classProbability; // Final class probability.
    private ArrayList<String> classes; // Classes.
    private ArrayList<SampleE3> testSet; // Final train set.
    private int[][] colisionMatrix; // Final colision matrix.

    /**
     * Constructor
     *
     * @param gaussianMatrix
     * @param classProbability
     * @param classes
     * @param testSet
     */
    public PreddictTestSetE3(ArrayList<ArrayList<Double>> gaussianMatrix, ArrayList<Double> classProbability, ArrayList<String> classes, ArrayList<SampleE3> testSet) {
        this.gaussianMatrix = gaussianMatrix;
        this.classProbability = classProbability;
        this.classes = classes;
        this.colisionMatrix = new int[this.classes.size()][this.classes.size()];
        this.testSet = testSet;
        this.preddictTestSet();
    }

    /**
     * Print colision matrix and final statistics.
     */
    public void printPreddictTestSet() {
        System.out.println();
        Integer success = new Integer(0);
        Integer fail = new Integer(0);
        System.out.print("Colision Matrix\n");
        for (int d = 0; d < this.classes.size(); d++) {
            System.out.print("  " + this.classes.get(d) + " ");
        }
        System.out.println();
        for (int b = 0; b < this.classes.size(); b++) {
            System.out.print(this.classes.get(b) + " ");
            for (int c = 0; c < this.classes.size(); c++) {
                System.out.print(colisionMatrix[b][c] + " ");
                if (b == c) {
                    success += colisionMatrix[b][c];
                } else {
                    fail += colisionMatrix[b][c];
                }
            }
            System.out.println();

        }
        System.out.println("\nSuccess: " + success);
        System.out.println("Fail: " + fail);
        System.out.println("Accuracy: " + ((double) success / (success + fail)) * 100 + "%");
    }

    /**
     * Create colision matrix from test set and class probability.
     */
    private void preddictTestSet() {
        for (int a = 0; a < this.testSet.size(); a++) { // Iterate over test set.
            ArrayList<Double> probSamplePerClass = new ArrayList(); // Probability of the sample for class
            Double evidence = new Double(0); // Evidencie Bayes   probability (denominator)
            for (int i = 0; i < this.classes.size(); i++) { // For each class calculate the bayes probability
                Double classProb = this.classProbability.get(i); // Get class probability
                Double samplePosteriori = classProb; // Sample posteriori first operand (numerator of Bayes   probability)
                for (int z = 0; z < this.testSet.get(a).getAttributes().size(); z++) { // Iterate over sample's attributes
                    Double firstOperand = new Double(0); // First operand (1/sqrt(2*avg*var))
                    Double secondOperand = new Double(0); // Second operand (e^(-(v-avg)^2/2*var))
                    Double avg = this.gaussianMatrix.get(i).get(z * 2); // Get attribute avg from gaussian matrix
                    Double var = this.gaussianMatrix.get(i).get((z * 2) + 1); // Get attribute var from gaussian matrix
                    Double expNumerator = Math.pow(Double.parseDouble(this.testSet.get(a).getAttributes().get(z)) - avg, 2); // Calulate (v-avg)^2 numerator
                    Double expDenominator = 2 * var; // Calculate 2*var denomitator
                    Double expTotal = -(expNumerator / expDenominator); // Calculate exp -(v-avg)^2/2*var)
                    secondOperand = Math.pow(Math.E, expTotal); // Calculate (e^(-(v-avg)^2/2*var))
                    firstOperand = 1 / (Math.sqrt(2 * avg * var)); // Calculate (1/sqrt(2*avg*var))
                    firstOperand *= secondOperand; // Calculate (1/sqrt(2*avg*var))*(e^(-(v-avg)^2/2*var))
                    samplePosteriori *= firstOperand; // Sample posteriori next operand (numerator of Bayes   probability)
                }
                probSamplePerClass.add(samplePosteriori); // Add sample posteriori to probability of the sample for class
                evidence += samplePosteriori; // Update evidente (denomitator of Bayes   probability)
            }
            // Update sample probability of the sample for class dividing by evidence
            for (int t = 0; t < probSamplePerClass.size(); t++) {
                probSamplePerClass.set(t, probSamplePerClass.get(t) / evidence); // Calculate final Bayes   probability per class
            }
            Integer greaterIndex = new Integer(0); // Greater index.
            Double greater = new Double(0); // Greater probability
            for (int t = 0; t < probSamplePerClass.size(); t++) { // Iterate over final Bayes   probability per class
                if (probSamplePerClass.get(t) > greater) { // If this probabilty is greater than max.
                    greater = probSamplePerClass.get(t); // Update greater value
                    greaterIndex = t; // Update greater probability index
                }
            }
            // Calculate colission matrix by greater index
            // If greater index class is equal to sample class (supervised)
            if (this.classes.get(greaterIndex).trim().equals(this.testSet.get(a).getKind().trim())) {
                colisionMatrix[greaterIndex][greaterIndex]++; // Update success prediction
            } else {
                // Update greater row index and sample's class index, fail prediction
                colisionMatrix[greaterIndex][this.classes.indexOf(this.testSet.get(a).getKind())]++;
            }
        }
    }
}
