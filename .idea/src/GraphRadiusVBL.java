//******************************************************************************
//
// File:    GraphRadiusVBL.java
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
 * Class GraphRadiusVBL provides a reduction variable for a CollatzSmp class shared by
 * multiple threads executing a parallel loop.
 * <p>
 * Class GraphRadiusVBL uses the reduction pattern as implemented by the library.
 * Local threads are created using the threadLocal method and the reduction
 * is handled by the library after the parallel loop execution is done. It uses
 * reduction function to merge the threads.
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class GraphRadiusVBL extends Tuple implements Vbl {

    long minVR, minVert;

    /**
     * Constructor to assign maximum hardware dependent value to the variable.
     */
    GraphRadiusVBL() {
        minVR = Long.MAX_VALUE;
        minVert = Long.MAX_VALUE;
    }

    /**
     * Clone the current thread. Interfaces' required function.
     *
     * @return return a new deep copy clone of current object
     */
    public Object clone() {
        try {
            GraphRadiusVBL vbl = (GraphRadiusVBL) super.clone();
            vbl.minVR = this.minVR;
            vbl.minVert = this.minVert;
            return vbl;
        } catch (Exception ex) {
            throw new RuntimeException("GraphRadiusVBL clone error");
        }
    }

    /**
     * this write the contents of the class to the outStream to be used as a tuple
     * @param outStream output stream object
     * @throws IOException thrown if error in communication occurs
     */
    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeLong(minVR);
        outStream.writeLong(minVert);
    }

    /**
     * reads the data from the inStream and puts the data into the object variables
     * @param inStream input stream object
     * @throws IOException thrown if error in communication occurs
     */
    @Override
    public void readIn(InStream inStream) throws IOException {
        this.minVR = inStream.readLong();
        this.minVert = inStream.readLong();

    }

    /**
     * reduces and stores the minimum of the Vbl object provided and the current
     * object. Interfaces' required function.
     *
     * @param vbl Reduction interface object.
     */
    public void reduce(Vbl vbl) {
        GraphRadiusVBL convertedGraphRadiusVBL = (GraphRadiusVBL) vbl;

        //keep the one which is smaller.
        if (this.minVR > convertedGraphRadiusVBL.minVR || (this.minVR == convertedGraphRadiusVBL.minVR && this.minVert > convertedGraphRadiusVBL.minVert)) {
            this.minVR = convertedGraphRadiusVBL.minVR;
            this.minVert = convertedGraphRadiusVBL.minVert;
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

        //keep the one which is smaller.
        if (this.minVR > compareCN || (this.minVR == compareCN && this.minVert > number)) {
            this.minVR = compareCN;
            this.minVert = number;
        }
    }

    /**
     * sets the thread variable to the one provided by the pj2 library via vbl
     * object.
     *
     * @param vbl Reduction interface object.
     */
    public void set(Vbl vbl) {
        GraphRadiusVBL convertedGraphRadiusVBL = (GraphRadiusVBL) vbl;
        this.minVR = convertedGraphRadiusVBL.minVR;
        this.minVert = convertedGraphRadiusVBL.minVert;
    }
}

