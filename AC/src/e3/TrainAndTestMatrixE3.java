package e3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * TrainAndTestMatrix class of naive bayes classifier. Class to generate train
 * and test matrix using input train percentage. UOC AC PRA - Antonio Díaz
 * Pozuelo
 *
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
public class TrainAndTestMatrixE3 {

    private FileReaderE3 file; // Readed file.
    private Integer trainPercentaje; // Input percetage.
    private ArrayList<SampleE3> trainSet; // Final train set.
    private ArrayList<SampleE3> testSet; // Final data set.

    /**
     * Constructor
     *
     * @param file
     * @param trainPercentaje
     */
    public TrainAndTestMatrixE3(FileReaderE3 file, Integer trainPercentaje) {
        this.file = file;
        this.trainPercentaje = trainPercentaje;
        this.trainSet = new ArrayList<>();
        this.testSet = new ArrayList<>();
        createTrainAndTestMatrix();
    }

    /**
     * Print train and test matrix.
     */
    public void printTrainAndTestMatrix() {
        System.out.println("\nTest Set: ");
        Iterator<SampleE3> it = this.testSet.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        System.out.println("\nTrain Set: ");
        Iterator ot = this.trainSet.iterator();
        while (ot.hasNext()) {
            System.out.println(ot.next().toString());
        }
    }

    /**
     * Create train and test matrix from dataset and using input percentage
     */
    private void createTrainAndTestMatrix() {
        SortedSet trainIdSet = new TreeSet<Integer>(); // Set with random numbers
        Random rand = new Random();
        int i = 0; // Valid random number generation counter.
        // While we have random samples to add into trainset
        while (i < (this.file.getNumSamples() * this.trainPercentaje) / 100) {
            Integer randomNum = rand.nextInt(this.file.getNumSamples()); // Generate random number
            if (!trainIdSet.contains(randomNum)) { // If random number is not yet into random trainset numbers
                trainIdSet.add(randomNum); // Add this number to set.
                i++; // Counter updated.
            }
        }
        ArrayList<SampleE3> dataSet = this.file.getDataSet();
        for (int j = 0; j < dataSet.size(); j++) { // Read original dataset
            if (trainIdSet.contains(new Integer(j))) { // If this sample is in random number trainset
                this.trainSet.add((SampleE3) dataSet.get(j)); // Add it to final trainset
            } else { // in other case, this sample is not in random number trainset
                this.testSet.add((SampleE3) dataSet.get(j)); // Add it to final testset
            }
        }
    }

    /**
     * Getters, no setters needed.
     * @return 
     */
    public ArrayList<SampleE3> getTrainSet() {
        return trainSet;
    }

    public ArrayList<SampleE3> getTestSet() {
        return testSet;
    }
}
