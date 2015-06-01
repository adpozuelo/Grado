package e3;

/**
 * Main class of naive bayes classifier. UOC AC PRA - Antonio Díaz Pozuelo
 *
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
public class E3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Input application control
         */
        if (args.length > 3 || args.length < 2) { // Input args control
            System.out.println("\nPlease, type file's name to read, train percentage and verbose mode: 'java -jar e3.jar filename.csv %train verbose'");
            System.out.println("\nExample: 'java -jar e3.jar filename.csv 50' <-- 50% samples to train and silent verbose mode");
            System.out.println("\nExample: 'java -jar e3.jar filename.csv 50 verbose' <-- 50% samples to train and verbose mode");
            System.out.println();
            System.exit(0);
        }
        if (Integer.parseInt(args[1]) > 90 || Integer.parseInt(args[1]) < 10) { // %train only can be between 10% and 90%, for optimization.
            System.out.println("\nERROR, train percentage must be from 10% to 90%");
            System.out.println();
            System.exit(0);
        }
        String verboseMode = "silent"; // By default, silen mode is activated.
        if (args.length == 3 && !args[2].trim().equals("verbose")) { // Control verbose mode input
            System.out.println("\nERROR, verbose mode incorrect");
            System.out.println("\nExample: 'java -jar e3.jar filename.csv 50' <-- 50% samples to train and silent verbose mode");
            System.out.println("\nExample: 'java -jar e3.jar filename.csv 50 verbose' <-- 50% samples to train and verbose mode");
            System.out.println();
            System.exit(0);
        } else if (args.length == 3) { // If all is correct, activate verbose mode.
            verboseMode = args[2].trim();
        }
        FileReaderE3 file = new FileReaderE3(args[0].trim()); // Read file
        TrainAndTestMatrixE3 trainAndTestMatrix = new TrainAndTestMatrixE3(file, Integer.parseInt(args[1]));
        if (verboseMode.equals("verbose")) {
            trainAndTestMatrix.printTrainAndTestMatrix(); // Print train and test matrix.
        }
        // Create gaussian matrix using train set.
        GaussianMatrixE3 gaussianMatrix = new GaussianMatrixE3(trainAndTestMatrix.getTrainSet(), file.getHeader(), file.getClasses());
        if (verboseMode.equals("verbose")) {
            gaussianMatrix.printGaussianMatrix();
        }
        // Ejecute test over gaussian matrix.
        PreddictTestSetE3 preddictTestSet = new PreddictTestSetE3(gaussianMatrix.getGaussianMatrix(), gaussianMatrix.getClassProbability(), file.getClasses(), trainAndTestMatrix.getTestSet());
        // Print colision matrix and accuracy statistics.
        preddictTestSet.printPreddictTestSet();
        System.out.println();
    }
}
