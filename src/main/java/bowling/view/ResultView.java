package bowling.view;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frames;

import java.util.stream.IntStream;

public class ResultView {
    private static final String BLANK_ONE = " ";
    private static final String BLANK_THREE = "   ";
    private static final String BLOCK_BORDER = "|";
    private static final String LABEL_NAME = "NAME";

    public static void print(PlayerName playerName, Frames frames){
        printPlayInformation();
        printLineSeparator();
        printResult(playerName, frames);
    }

    private static void printResult(PlayerName playerName, Frames frames){
        printName(playerName.getName());
    }


    private static void printPlayInformation(){
        printNameColumn(LABEL_NAME);
        IntStream.range(1, 11)
                .forEach(it -> printOneBlockWith(convertFrameNumberToString(it)));
    }

    private static void printNameColumn(String name){
        printBlockBorder();
        printOneBlockWith(name);
    }

    private static void printName(String name){
        printBlockBorder();
        printMessage(BLANK_ONE);
        printOneBlockWith(name);
        printMessage(BLANK_ONE);
    }

    private static void printOneBlockWith(String message){
        printBlankBlock();
        printMessage(message);
        printBlankBlock();
        printBlockBorder();
    }

    private static void printMessage(String message){
        System.out.print(message);
    }

    private static String convertFrameNumberToString(int number){
        String stringNumber = (number >= 10) ? String.valueOf(number) : "0"+ number;
        return stringNumber;
    }

    private static void printBlankBlock(){
        System.out.print(BLANK_THREE);
    }

    private static void printBlockBorder(){
        System.out.print(BLOCK_BORDER);
    }

    private static void printLineSeparator(){
        System.out.println();
    }
}
