package model;

import utils.IOUtils;

import java.util.*;

public class Analyzer {

    private final List<Integer> results = new ArrayList<>();
    private final Set<Integer> processedCells = new HashSet<>();
    private final Set<Integer> queuedCells = new HashSet<>();
    private BitArray field = new BitArray(LandEvaluator.MAX_HEIGHT, LandEvaluator.MAX_WIDTH);


    public Analyzer(){  }

    //initialize space to store land data. 0: processed land, 1: processed unit area in active set; 2:  on 3 - default/unprocessed
    public List<Integer> computeAreas(){
        initializeRectangles();
        while(field.findCellContainingValue(0) > -1){
            processPatch();
        }
        Collections.sort(results);
        System.out.println(results.toString());

        return results;

    }



    private void processPatch() {
        int cellID = field.findCellContainingValue(0);
        processedCells.clear();
        queuedCells.clear();
        queuedCells.add(cellID);

        int runningSum = 0;

        while(!queuedCells.isEmpty()){
            runningSum = iteratePatch(runningSum);
        }

        results.add(runningSum);
    }





    private int iteratePatch(int runningSum) {

        Iterator <Integer> ir = queuedCells.iterator();

        if(ir.hasNext()) {

            int cellID = ir.next();
            processedCells.add(cellID);
            queuedCells.remove(cellID);

            if (0 == field.getCellValue(cellID)) {

                field.setCellState(cellID, (byte) 1);
                runningSum++;

                for (int neighbor : field.getNeighborIDs(cellID)) {
                    if (!(neighbor < 0 || processedCells.contains(neighbor))) {
                        queuedCells.add(neighbor);
                    }


                }
            }
        }

        return runningSum;

    }



    private void initializeRectangles() {


        for (int[] rectangle : IOUtils.parseInput()) {

            int minHeight = Math.max(0, rectangle[0]);
            int minWidth = Math.max(0, rectangle[1]);

            int maxHeight = Math.min(LandEvaluator.MAX_HEIGHT-1, rectangle[2]);
            int maxWidth = Math.min(LandEvaluator.MAX_WIDTH-1, rectangle[3]);


            for (int vPos = minHeight; vPos <= maxHeight ; vPos++) {
                for (int hPos = minWidth; hPos <= maxWidth ; hPos++) {
                    field.setCellState(vPos, hPos, (byte) 3);
                }
            }
        }
    }


}
