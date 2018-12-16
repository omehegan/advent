package com.nerdnetworks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        /* Naive solution:
        DONE: Read file into array of strings
        DONE: Iterate array
        DONE: String parse each element to figure out math operation and value
        DONE: Cast value to integer
        DONE: Perform operation
        */

	    Integer startingFreq = 0;
	    Integer endingFreq = null;
	    String[] inputValues = null; // TODO: What is a better way to initialize this? Unclear how to get the required array size back from the readLines method.
        Map< Integer,Integer> foundFreqs = new HashMap< Integer,Integer>();

	    try {
            inputValues = readLines("/Users/owen/source/advent/data/day-1-input.txt");
        } catch (IOException e) {
	        System.out.println("Failed to read input file.");
        }

        for (String str : inputValues) {
            String[] parts = str.split("(?<=-)|(?<=\\+)");

            if (parts[0].equals(String.valueOf('+'))) {
	            Integer value = Integer.valueOf(parts[1]);
	            endingFreq = startingFreq + value;
	            startingFreq = endingFreq;
	            if (foundFreqs.containsKey(endingFreq)) {
	                System.out.println(String.format("Repeated frequency: %s", endingFreq));
                } else {
                    foundFreqs.put(endingFreq, null);
                }
            } else if (parts[0].equals(String.valueOf('-'))){
                Integer value = Integer.valueOf(parts[1]);
                endingFreq = startingFreq - value;
                startingFreq = endingFreq;
                if (foundFreqs.containsKey(endingFreq)) {
                    System.out.println(String.format("Repeated frequency: %s", endingFreq));
                } else {
                    foundFreqs.put(endingFreq, null);
                }
            } else {
	            System.out.println("No match.");
            }
        }

        System.out.println(endingFreq);
    }

    private static String[] readLines(String filename) throws IOException {
        /*
        Why FileReader and BufferedReader?
        Why List and ArrayList?
         */

        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
}
