package utils;

import model.LandEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils {

    private static boolean isUseScanner = true;


    public static List<int[]> parseInput(){
        return parseInput(true);

    }



    public static List<int[]> parseInput(boolean isUseScanner){

        if(isUseScanner){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter coordinates of rectangles");
            return parseInput(scanner.nextLine());

        }else{
            return parseInput(LandEvaluator.INPUT_DATA);
        }

    }

    public static List<int[]> parseInput(String rawData){
        return parseInput(rawData, false);
    }




    public static List<int[]> parseInput(String rawData, boolean isTest){

        List<int[]> list = new ArrayList<>();
        Pattern p = Pattern.compile("-?\\d+");
        String[] rectangles = rawData.split(",");
        for (String rawRectangleCoordinates: rectangles){

            Matcher m = p.matcher(rawRectangleCoordinates);

            int[] parsedRectangle = new int[4];
            int i = 0;

            while (m.find()) {

                if(i < parsedRectangle.length){
                    parsedRectangle[i] = Integer.parseInt(m.group());
                    i++;
                }else{
                    System.out.println("Invalid data: rectangle must be defined by four numbers");
                    return isTest ? null : parseInput();
                }
            }
            if(i<4){
                System.out.println("Invalid data: rectangle must be defined by four numbers");
                return isTest ? null : parseInput();
            }
            list.add(parsedRectangle);
        }
        return list;

    }




}
