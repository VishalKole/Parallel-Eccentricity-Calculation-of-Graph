//******************************************************************************
//
// File:    Graph.java
//
// This Java source file is part of the parallel programming assignment 2 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.util.IntList;
import edu.rit.util.BitSet;

/**
 * class Graph creates a graph and then returns an inner class object to do a BFS traversal
 * on the graph and return the longest length in the graph from the given node.
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 15-October-2017
 */
public class Graph {

    private IntList graph[];

    /**
     * constructor to create graph with the class with given GraphSpec object
     */
    Graph(GraphSpec gs) {
        this.graph = this.createGraph(gs);
    }

    /**
     * private method to generate graph on the GraphSpec object and return IntList array
     * which is adjacency list.
     *
     * @param gs GraphSpec object
     * @return adjacency list representation of graph
     */
    private IntList[] createGraph(GraphSpec gs) {

        IntList graph[] = new IntList[gs.V()];

        //create list
        for (int i = 0; i < gs.V(); ++i) {
            graph[i] = new IntList();
        }

        Edge e = new Edge();

        //add every edge to the IntList variable list and to the other vertex list as well
        // do it for all the edges
        for (int i = 0; i < gs.E(); i++) {
            gs.nextEdge(e);
            graph[e.v1].addLast(e.v2);
            graph[e.v2].addLast(e.v1);
        }
        return graph;
    }


    /**
     * creates an instance of inner class GraphEccentricity and returns it to the calling function
     * @return
     */
    public GraphEccentricity newGraphEccentricity() {
        GraphEccentricity r = new GraphEccentricity();
        r.queue = new ArrayQueue(graph.length);
        r.visited_set = new BitSet(graph.length);
        return r;
    }

    /**
     * Inner class to provide the Eccentricity calculator and to store the data structures required
     */
    class GraphEccentricity {

        private BitSet visited_set;
        private ArrayQueue queue;
        int len;

        /**
         * clear the contents for further use
         */
        void clear() {
            visited_set.clear();
            len = 0;
        }


        /**
         * this does the BFS traversal on the given vertex and returns the length
         *
         * @param start the starting vertex to use
         * @return length of the longest path
         */
        int BFS(int start) {

            //clearing the contents before execution begins
            this.clear();

            //adding start to queue
            visited_set.add(start);
            queue.add(start);

            //marker for the end of current level in the queue
            queue.add(-1);

            //looping through the queue to search for all the vertices visited
            while (!queue.isEmpty()) {
                start = queue.poll();

                //using just one queue to get the level of the currently visited nodes
                if (start == -1) {
                    if (!queue.isEmpty()) {
                        ++len;
                        queue.add(-1);
                    }
                    continue;
                }

                // adding all the neighbours in the queue
                for (int counter = 0; counter < graph[start].size(); ++counter) {
                    int neighbour = graph[start].get(counter);
                    if (!visited_set.contains(neighbour)) {
                        visited_set.add(neighbour);
                        queue.add(neighbour);
                    }
                }
            }

            //returning the length
            if (visited_set.size() != graph.length)
                return Integer.MAX_VALUE;
            return len;

        }
    }
}