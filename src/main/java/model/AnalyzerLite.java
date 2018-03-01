package model;

import utils.IOUtils;

import java.util.*;

public class AnalyzerLite{

    private final List<Integer> results = new ArrayList<>();
    private BitArray field = new BitArray(LandEvaluator.MAX_HEIGHT, LandEvaluator.MAX_WIDTH);

    public AnalyzerLite(){   }




    public List<Integer> computeAreas(){
        field.setRectangles(IOUtils.parseInput(LandEvaluator.IS_USE_SCANNER));
        return analyze();
    }

    public List<Integer> computeAreas(String data){
        field.setRectangles(IOUtils.parseInput(data));
        return analyze();
    }

    private List<Integer> analyze() {

        while(field.findCellContainingValue(0) > -1){
            processPatch();
        }
        Collections.sort(results);
        System.out.println(results.toString());

        return results;
    }


    private void processPatch() {
        int cellID = field.findCellContainingValue(0);
        field.setCellState(cellID, (byte) 1);
        int[] sumAndLastIncrement = {1, 1};
        while(sumAndLastIncrement[1] > 0){
            iteratePatch(sumAndLastIncrement);
        }
        //System.out.println("runningSum "+sumAndLastIncrement[0]);
        results.add(sumAndLastIncrement[0]);
    }





    private void  iteratePatch(int[] sumAndLastIncrement) {  //true means iteration completed
        sumAndLastIncrement[1] = 0;
        for (int i = 0; i <   LandEvaluator.MAX_HEIGHT * LandEvaluator.MAX_WIDTH; i++) {

            int[] neighbors = field.getNeighborIDs(i);
            if (field.getCellValue(i) == 1) {

                for (int neighbor : field.getNeighborIDs(i)) {

                    if (neighbor >= 0) {
                        if (field.getCellValue(neighbor) == 0) {
                            field.setCellState(neighbor, (byte) 1);
                            sumAndLastIncrement[1] += 1;
                        }
                    }
                }

            } else if (field.getCellValue(i) == 0) {

                for (int neighbor : field.getNeighborIDs(i)) {

                    if (neighbor >= 0) {
                        if (field.getCellValue(neighbor) == 1) {
                            field.setCellState(i, (byte) 1);
                            sumAndLastIncrement[1] += 1;
                            break;
                        }
                    }
                }
            }
        }
            sumAndLastIncrement[0] += sumAndLastIncrement[1];
    }

}
