package model;

public class LandEvaluator {

    public static final int MAX_HEIGHT = 400;
    public static final int MAX_WIDTH = 600;
    public static  boolean IS_USE_SCANNER = true;
    public static  String[] args;
    public static  String INPUT = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”} ";

    public static void main(String[] args) {
        LandEvaluator.args = args;




       //Analyzer a = new Analyzer();
        AnalyzerLite aLite = new AnalyzerLite();

      // /{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}
      // /{“-1 3 80 4”, “4 80 400 81”, “120 52 135 547”, “260 52 275 547”} “-1 3 80 4”, “79 2 79 400“



    }

    public class IS_USE_SCANNER {
    }
}
