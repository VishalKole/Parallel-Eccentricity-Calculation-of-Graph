//******************************************************************************
//
// File:    ReduceTask.java
//
// This Java source file is part of the parallel programming assignment 2 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;
/**
 * This class takes the tuples that match the reduction variables and reduces it
 * it the prints the result on the console.
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 15-October-2017
 */
public class ReduceTask extends Task {

    /**
     * main program to run the task
     * @param args arguments passed as command line. Ignored.
     * @throws Exception throws exception if it fails
     */
    public void main(String[] args) throws Exception {

        //reduction variables
        GraphDiameterVBL diameter = new GraphDiameterVBL();
        GraphRadiusVBL radius = new GraphRadiusVBL();

        //templates to match
        GraphDiameterVBL templatemax = new GraphDiameterVBL();
        GraphRadiusVBL templatemin = new GraphRadiusVBL();

        //storage variable
        GraphDiameterVBL maxt;
        GraphRadiusVBL mint;

        //get the diameter results
        while ((maxt = tryToTakeTuple(templatemax)) != null)
            diameter.reduce(maxt);

        //get the radius results
        while ((mint = tryToTakeTuple(templatemin)) != null)
            radius.reduce(mint);

        //print the output in the format mentioned
        System.out.println("Radius = "+ (radius.minVR==Integer.MAX_VALUE?"infinity":radius.minVR) );
        System.out.println("Central vertex = " + radius.minVert);
        System.out.println("Diameter = " + (diameter.maxVD==Integer.MAX_VALUE?"infinity":diameter.maxVD));
        System.out.println("Peripheral vertex = " + diameter.maxVert);
    }
}
