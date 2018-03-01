import org.junit.Test;
import utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IOTester {

    String[] str = new String[5];
    boolean[] isValid = {true, true, false, false, false};
    List<int[]>[] expectedResults = new ArrayList[str.length];

    {


        str[0] = "{“10 10 200 70“, “48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}"; //valid
        str[1] = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}"; //valid
        str[2] = "{“48 192 351 ”, “207 48 392 351 407”, “120 52 135 547”, “260 52 275 547”}"; //incorrect
        str[3] = "{“48 192 351 207” “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}"; //incorrect
        str[4] = "{“48.12 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}"; //incorrect

        List<int[]> sample0 = new ArrayList<int[]>();


        sample0.add(new int[]{10, 10, 200, 70});
        sample0.add(new int[]{48, 192, 351, 207});
        sample0.add(new int[]{48, 392, 351, 407});
        sample0.add(new int[]{120, 52, 135, 547});
        sample0.add(new int[]{260, 52, 275, 547});
        expectedResults[0] = sample0;

        List<int[]> sample1 = new ArrayList<int[]>();
        sample1.add(new int[]{48, 192, 351, 207});
        sample1.add(new int[]{48, 392, 351, 407});
        sample1.add(new int[]{120, 52, 135, 547});
        sample1.add(new int[]{260, 52, 275, 547});
        expectedResults[1] = sample1;
        expectedResults[2] = null;
        expectedResults[3] = null;
        expectedResults[4] = null;


    }

    @Test
    public void testArrayRecord() {
        for (int i = 0; i < str.length; i++) {
            List<int[]> parsedData = IOUtils.parseInput(str[i], true);

            if (isValid[i]) {

                assertEquals(expectedResults[i].size(), parsedData.size());

                for (int j = 0; j < parsedData.size(); j++) {

                    assertArrayEquals(expectedResults[i].get(j), parsedData.get(j));
                }

                for (int k = 0; k < parsedData.get(i).length; k++) {

                }


            } else {
                assertNull(parsedData);
            }

        }
    }


}
