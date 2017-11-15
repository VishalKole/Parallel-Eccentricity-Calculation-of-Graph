//******************************************************************************
//
// File:    GraphDiameterVBL.java
//
// This Java source file is part of the parallel programming assignment 1 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Vbl;
import edu.rit.pj2.Tuple;

import java.io.IOException;

/**
 * Class GraphDiameterVBL provides a reduction variable for a CollatzSmp class shared by
 * multiple threads executing a parallel loop.
 * <p>
 * Class GraphDiameterVBL uses the reduction pattern as implemented by the library.
 * Local threads are created using the threadLocal method and the reduction
 * is handled by the library after the parallel loop execution is done. It uses
 * reduction function to merge the threads.
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class GraphDiameterVBL extends Tuple implements Vbl {

    long maxVD, maxVert;

    /**
     * Clone the current thread. Interfaces' required function.
     *
     * @return return a new deep copy clone of current object
     */
    public Object clone() {
        try {
            GraphDiameterVBL vbl = (GraphDiameterVBL) super.clone();
            vbl.maxVD = this.maxVD;
            vbl.maxVert = this.maxVert;
            return vbl;

        } catch (Exception ex) {
            throw new RuntimeException("GraphDiameterVBL clone error");
        }
    }

    /**
     * this write the contents of the class to the outStream to be used as a tuple
     * @param outStream output stream object
     * @throws IOException thrown if error in communication occurs
     */
    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeLong(maxVD);
        outStream.writeLong(maxVert);
    }

    /**
     * reads the data from the inStream and puts the data into the object variables
     * @param inStream input stream object
     * @throws IOException thrown if error in communication occurs
     */
    @Override
    public void readIn(InStream inStream) throws IOException {
        this.maxVD = inStream.readLong();
        this.maxVert = inStream.readLong();
    }

    /**
     * reduces and stores the maximum of the Vbl object provided and the current
     * object. Interfaces' required function.
     *
     * @param vbl Reduction interface object.
     */
    public void reduce(Vbl vbl) {
        GraphDiameterVBL convertedGraphDiameterVBL = (GraphDiameterVBL) vbl;

        //keep the one which is higher.
        if (this.maxVD < convertedGraphDiameterVBL.maxVD || (this.maxVD == convertedGraphDiameterVBL.maxVD && this.maxVert < convertedGraphDiameterVBL.maxVert)) {
            this.maxVD = convertedGraphDiameterVBL.maxVD;
            this.maxVert = convertedGraphDiameterVBL.maxVert;
        }
    }

    /**
     * Custom reduce function with two integer input to assign and reduce the
     * variable for when it is executed in the thread.
     *
     * @param compareCN Collatz Conjecture number.
     * @param number    the number on which CN was computed.
     */
    public void reduce(long compareCN, long number) {

        //keep the one which is higher.
        if (this.maxVD < compareCN || (this.maxVD == compareCN && this.maxVert < number)) {
            this.maxVD = compareCN;
            this.maxVert = number;
        }
    }

    /**
     * sets the thread variable to the one provided by the pj2 library via vbl
     * object.
     *
     * @param vbl Reduction interface object.
     */
    public void set(Vbl vbl) {
        GraphDiameterVBL convertedGraphDiameterVBL = (GraphDiameterVBL) vbl;
        this.maxVD = convertedGraphDiameterVBL.maxVD;
        this.maxVert = convertedGraphDiameterVBL.maxVert;
    }
}
