package e3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * FileReader class of naive bayes classifier. Class to read train and test file
 * of data samples. UOC AC PRA - Antonio Díaz Pozuelo
 *
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
public class FileReaderE3 {

    private String fileName; // File to read.
    private ArrayList<SampleE3> dataSet; // Dataset of file samples.
    private ArrayList<String> header; // Header of dataset.
    private Integer numSamples; // Total number of dataset samples.
    private Integer numAttributes; // Total number of attributes per sample.
    private ArrayList<String> classes; // Dataset's classes.
    private Integer numOfClasses; // Total number of classes.

    /**
     * Constructor.
     *
     * @param fileName File's name to read.
     */
    public FileReaderE3(String fileName) {
        this.fileName = fileName;
        this.dataSet = new ArrayList<>();
        this.header = new ArrayList<>();
        this.readFile();
        this.numSamples = this.dataSet.size();
        this.numAttributes = this.header.size();
        this.classes = new ArrayList<>();
        this.numOfClasses = this.getClasses().size();
    }

    /**
     * Getters, no setters needed.
     *
     * @return
     */
    public Integer getNumSamples() {
        return numSamples;
    }

    public Integer getNumAttributes() {
        return numAttributes;
    }

    public ArrayList<SampleE3> getDataSet() {
        return dataSet;
    }

    public ArrayList<String> getHeader() {
        return header;
    }

    public ArrayList<String> getClasses() {
        Iterator<SampleE3> it = this.dataSet.iterator();
        while (it.hasNext()) {
            SampleE3 sampleTmp = it.next();
            if (!this.classes.contains(sampleTmp.getKind())) {
                this.classes.add(sampleTmp.getKind());
            }
        }
        return classes;
    }

    public Integer getNumOfClasses() {
        return numOfClasses;
    }

    /**
     * Print dataset readed from file.
     */
    public void printReadedDataSet() {
        System.out.println("\nFilename: " + this.fileName);
        System.out.println("Total Num Attributes (class incl.): " + this.numAttributes);
        System.out.println("Total Num Samples: " + this.numSamples);
        System.out.print("Clases (" + this.numOfClasses + "): ");
        Iterator<String> at = this.classes.iterator();
        while (at.hasNext()) {
            System.out.print(at.next() + " ");
        }
        System.out.println();
        System.out.println();
        Iterator<String> st = this.header.iterator();
        while (st.hasNext()) {
            System.out.print(st.next() + " ");
        }
        System.out.println();
        Iterator<SampleE3> it = this.dataSet.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    /**
     * Read file from disk.
     */
    private void readFile() {
        try {
            File file = new File(this.fileName);
            java.io.FileReader fileReader = new java.io.FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0; // Control in which line we are.
            String line; // Line to read.
            while ((line = bufferedReader.readLine()) != null) { // While lines of file exist.
                StringTokenizer st = new StringTokenizer(line, ","); // Split line by commas (CSV)
                if (i > 0) { // If line is not the first, it contains a sample (most cases).
                    String kind = st.nextToken(); // Sample's class.
                    ArrayList<String> elements = new ArrayList<>(); // Sample's attributes collection.
                    while (st.hasMoreElements()) { // While line contains tokens.
                        elements.add(st.nextToken().trim()); // Add attribute to sample's attributes collection.
                    }
                    SampleE3 sample = new SampleE3(kind, elements); // Create new sample.
                    this.dataSet.add(sample); // Add sample to dataset.
                } else { // If line is the first, it contains a header (first case).
                    while (st.hasMoreElements()) { // While line contains tokens.
                        this.header.add(st.nextToken().trim()); // Add tokens to header.
                    }
                }
                i++;
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("\nERROR: Invalid file name\n");
            e.printStackTrace();
        }
    }
}
