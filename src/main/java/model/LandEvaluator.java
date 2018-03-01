package model;

import java.util.List;

public class LandEvaluator {

    public static final int MAX_HEIGHT = 400;
    public static final int MAX_WIDTH = 600;

    public static  boolean IS_USE_SCANNER = true;  //true: read console, false: parse INPUT_DATA
    public static  String INPUT_DATA = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}";

    public static void main(String[] args) {

        //implemented two algorithms.

        /*
        //first approach: store a collection of pending cells
       Analyzer a = new Analyzer();
       List<Integer> areas = a.computeAreas();
       */

        //second approach: get/set cell flag to track progress. BitArray stores 4 cells per byte
        AnalyzerLite aLite = new AnalyzerLite();
        List<Integer> areas2 = aLite.computeAreas();

    }
}
