package model;

import java.util.List;

public class BitArray {


    private int height;
    private int width;
    public static  final int NUMBER_OF_STATES = 4;
    public static final int POINTS_PER_BYTE = 4;
    private byte[] array;


    public BitArray(int height, int width) {

        this.height = height;
        this.width = width;

        int arraySize = (width * height)%POINTS_PER_BYTE ==0 ? (width * height)/POINTS_PER_BYTE : (width * height)/POINTS_PER_BYTE + 1;
        array = new byte[arraySize];

    }

    public byte computeNewValue( byte initialValue, int offset, byte newValue) {

        if(((newValue >> 1) & 1) == 1) {
            initialValue |= 1 << (offset * 2+1);
        }else {
            initialValue &= ~(1 << (offset * 2+1));
        }

        if((newValue & 1) == 1) {
            initialValue |= 1 << (offset * 2);
        }else {
            initialValue &= ~(1 << (offset * 2));
        }

        return initialValue;
    }



    public void setCellState(int vPos, int hPos, byte newValue) {
        int pos = hPos + vPos * width;
        setCellState(pos, newValue);
    }

    public int[] findValueIndexes(){
        int[] valuesFoundAtCellIndex = {-1, -1, -1, -1};
        for (int cellID = 0; cellID < height *  width ; cellID++) {
            byte cellValue = getCellValue(cellID) ;
            if(valuesFoundAtCellIndex[cellValue] < 0 ){
                valuesFoundAtCellIndex[cellValue] = cellID;
            }

            if(valuesFoundAtCellIndex[0] >= 0 && valuesFoundAtCellIndex[1] >= 0 && valuesFoundAtCellIndex[2] >= 0 && valuesFoundAtCellIndex[3] >= 0){
                return valuesFoundAtCellIndex;
            }

        }
        return valuesFoundAtCellIndex;
    }

    public int findCellContainingValue(int value){
        for (int cellID = 0; cellID < height *  width ; cellID++) {
            if(value == getCellValue(cellID)){
                return cellID;
            }
        }
        return -1;
    }

    public boolean isAnalysisComplete(){
        for (int cellID = 0; cellID < height *  width ; cellID++) {
            if(3 != getCellValue(cellID)){
                return false;
            }
        }
        return true;
    }

    public boolean isPatchComplete(){

        for (int cellID = 0; cellID < height *  width ; cellID++) {
            if(1 == getCellValue(cellID)){
                return true;
            }
        }
        return false;
    }


    public void setCellState(int pos, byte newValue){
        int index = (pos) / POINTS_PER_BYTE;
        int offset = (pos % POINTS_PER_BYTE);

        array[index] = computeNewValue(array[index], offset, newValue);
    }

    public void replaceAll(byte previousValue, byte newValue){

        for (int cellID = 0; cellID < height *  width ; cellID++) {
            if(previousValue == getCellValue(cellID)){
                setCellState(cellID, newValue);
            }
        }
    }


    public byte getCellValue(int vPos, int hPos) {
        int cellID = hPos + vPos * width;
        return getCellValue( cellID);
    }

    public byte getCellValue(int cellID) {

        int index = cellID/POINTS_PER_BYTE;
        int offset = (cellID%POINTS_PER_BYTE) * 2;

        int result = (array[index] >> offset) & (3);

        return (byte) result;


    }

    public String printArray() {

        StringBuilder sb = new StringBuilder("");
        String[] s = {"!", " ", " ", "*"};

        String t1 = "\n|";

        for (int hPos = 0; hPos < width; hPos++){
            sb.append("-");
        }

        for (int vPos = 0; vPos < height; vPos++) {

            sb.append(t1);
            t1 = "|\n|";
            for (int hPos = 0; hPos < width; hPos++) {

                int cellValue = getCellValue(vPos,hPos);



                sb.append(s[cellValue]);
            }
        }

        sb.append("|\n");
        System.out.println("width "+ width +" height "+ height);

        for (int hPos = 0; hPos < width; hPos++){
            sb.append("-");
        }
        return sb.toString();
    }


    public int[] getNeighborIDs(int cellID) {
        int hPos = cellID%width;
        int vPos = cellID/width;
        int[]  neighbors = new int[4];


        neighbors[0] = hPos > 0  ? cellID-1 : -1;
        neighbors[1] = hPos < width-1 ? cellID+1 : -1;
        neighbors[2] = vPos > 0 ? (cellID-width) : -1;
        neighbors[3] = vPos < height-1 ? (cellID+width) : -1;

        return neighbors;
    }

    public void setRectangles(List<int[]> input){
        for (int[] rectangle : input) {
            int minHeight = Math.max(0, rectangle[0]);
            int minWidth = Math.max(0, rectangle[1]);

            int maxHeight = Math.min(LandEvaluator.MAX_HEIGHT-1, rectangle[2]);
            int maxWidth = Math.min(LandEvaluator.MAX_WIDTH-1, rectangle[3]);


            for (int vPos = minHeight; vPos <= maxHeight ; vPos++) {
                for (int hPos = minWidth; hPos <= maxWidth ; hPos++) {
                    setCellState(vPos, hPos, (byte) 3);
                }
            }
        }
    }
}
