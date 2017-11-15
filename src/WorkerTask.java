//******************************************************************************
//
// File:    WorkerTask.java
//
// This Java source file is part of the parallel programming assignment 2 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;
import edu.rit.util.Instance;
import edu.rit.pj2.Loop;

/**
 * This class takes the tuple that match the reduction variables and reduces it
 * it the prints the result on the console.
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 15-October-2017
 */
public class WorkerTask extends Task {

    private GraphDiameterVBL dia;
    private GraphRadiusVBL rad;
    Graph worker_graph;

    /**
     * the main program creates graph for the current worker and sends it to all the threads
     * It the reduces the result using the reduction variable and puts the tuple into tuple space
     * for the ReduceTask to take it and display the results
     *
     * @param args
     */
    public void main(String[] args) throws Exception {

        //creating the graph
        dia = new GraphDiameterVBL();
        rad = new GraphRadiusVBL();
        worker_graph = new Graph((GraphSpec) Instance.newInstance(args[0]));

        //worker thread execution
        workerFor().schedule(guided).exec(new Loop() {

            //reduction variable thread copy
            GraphDiameterVBL thrDia;
            GraphRadiusVBL thrRad;

            //Eccentricity calculator thread copy
            Graph.GraphEccentricity thrEcc;

            //instantiating the variables
            public void start() {
                thrDia = threadLocal(dia);
                thrRad = threadLocal(rad);
                thrEcc = worker_graph.newGraphEccentricity();
            }

            //running BFS for the current vertex provided by the loop
            public void run(int vert) {
                int res = thrEcc.BFS(vert);
                thrDia.reduce(res, vert);
                thrRad.reduce(res, vert);
            }
        });

        //put the tuple to be picked up by ReduceTask
            putTuple(dia);
            putTuple(rad);
    }
}
