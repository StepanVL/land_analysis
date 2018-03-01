import model.AnalyzerLite;
import model.BitArray;
import model.LandEvaluator;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Tester {


    private final int TEST_POINTS_COUNT = 3;


    @Test
    public void  testArrayRecord() {
        BitArray ba = new BitArray(LandEvaluator.MAX_HEIGHT, LandEvaluator.MAX_WIDTH);
        byte[][] testArray = new byte[LandEvaluator.MAX_HEIGHT][LandEvaluator.MAX_WIDTH];
        Random r = new Random();

        for (int j = 0; j < TEST_POINTS_COUNT; j++) {
            int vPos = r.nextInt(LandEvaluator.MAX_HEIGHT);
            int hPos = r.nextInt(LandEvaluator.MAX_WIDTH);
            testArray[vPos][hPos] =  (byte) (r.nextInt(BitArray.NUMBER_OF_STATES));

            ba.setCellState(vPos, hPos, testArray[vPos][hPos]);

        }




        for (int vPos = 0; vPos < testArray.length; vPos++) {
            for (int hPos = 0; hPos < testArray[0].length; hPos++) {


                assertEquals(testArray[vPos][hPos], ba.getCellValue(vPos,hPos));
            }
        }
    }

    @Test
    public void  testValueChecker() {

        BitArray ba = new BitArray(LandEvaluator.MAX_HEIGHT, LandEvaluator.MAX_WIDTH);

        ba.setCellState(0, 2, (byte) 1);
        int[] expectedResult1 = {0, 2, -1, -1};
        int[] computedResult = ba.findValueIndexes();


        assertArrayEquals(expectedResult1, computedResult );

        ba.setCellState(0, 2, (byte) 0);
        ba.setCellState(0, 3, (byte) 2);
        ba.setCellState(0, 1, (byte) 3);
        int[]  expectedResult2  = {0, -1, 3, 1};
        assertArrayEquals(expectedResult2, ba.findValueIndexes());


    }


    @Test
    public void  testAnalysis() {

        AnalyzerLite a = new AnalyzerLite();
        int[] expectedResult = {22816, 192608};
        List<Integer> results = a.computeAreas(LandEvaluator.INPUT);

        assertEquals(expectedResult.length, results.size());
        for (int i = 0; i <results.size() ; i++) {
            assertEquals((long) expectedResult[i], (long) results.get(i));
        }

        BitArray ba = new BitArray(LandEvaluator.MAX_HEIGHT, LandEvaluator.MAX_WIDTH);

        ba.setCellState(0, 2, (byte) 1);
        int[] expectedResult1 = {0, 2, -1, -1};
        int[] computedResult = ba.findValueIndexes();

        assertArrayEquals(expectedResult1, computedResult );

        ba.setCellState(0, 2, (byte) 0);
        ba.setCellState(0, 3, (byte) 2);
        ba.setCellState(0, 1, (byte) 3);
        int[]  expectedResult2  = {0, -1, 3, 1};
        assertArrayEquals(expectedResult2, ba.findValueIndexes());


    }



}