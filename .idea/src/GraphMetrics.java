//******************************************************************************
//
// File:    GraphMetrics.java
//
// This Java source file is part of the parallel programming assignment 2 for the
// partial completion of the coursework
//
//******************************************************************************


import edu.rit.pj2.Job;
import edu.rit.util.Instance;

/**
 * GraphMetrics calculates the diameter and radius of a graph given as a input. the graph should be a GraphSpec
 * interface class and should implement it. the program calculates it on a cluster and displays result to the
 * console output.
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 15-October-2017
 */
public class GraphMetrics extends Job {

    public void main(String[] args)  {

    try {
        GraphSpec gs = this.ValidateInput(args);

        int vertices = gs.V();

        //scheduling
        masterSchedule(guided);

        //master for to do the work for K workers
        masterFor(0, vertices - 1, WorkerTask.class).args(args);

        //at finish this reduces the tuples
        rule().atFinish().task(ReduceTask.class);
    }
    catch (Exception e){
        System.out.println(e.getCause());
    }

    }

    /**
     * Validation program to validate on the input
     *
     * @param input input parameters
     * @return GraphSpec object
     * @throws IllegalArgumentException thrown if parameters not valid
     */
    private GraphSpec ValidateInput(String input[]) throws Exception {

        GraphSpec gs;
        //check if arguments equal to two
        if (input.length != 1)
            throw new IllegalArgumentException("Arguments are not exactly equal to one.");

        gs = (GraphSpec) Instance.newInstance(input[0]);


        long V = gs.V();
        long E = gs.E();

        //check if vertex are correct
        if ((V < 1))
            throw new IllegalArgumentException("Invalid Vertex number. should be greater than 0. ");

        //check if edges are correct
        if (E < 0 || E > (V * (V - 1) / 2))
            throw new IllegalArgumentException("Invalid Edge number. should be greater than -1 and less than " +
                    " (V * (V - 1) / 2)");

        return gs;
    }
}
