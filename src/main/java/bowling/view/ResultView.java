package bowling.view;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.*;

public class ResultView {
    private static final String BLANK_ONE = " ";
    private static final String BLANK_THREE = "   ";
    private static final String BLANK_FOUR = "    ";
    private static final String BLANK_FIVE = "     ";
    private static final String BLOCK_BORDER = "|";
    private static final String LABEL_NAME = "NAME";
    private static final String SYMBOL_STRIKE = "X";
    private static final String SYMBOL_GUTTER = "-";
    private static final String DELIMITER_SPARE = "/";


    public static void print(PlayerName playerName, Frames frames){
        printPlayInformation();
        printLineSeparator();
        printResult(playerName, frames);
    }

    private static void printResult(PlayerName playerName, Frames frames){
        printName(playerName.getName());
        printPointResult(frames);
    }

    private static void printPointResult(Frames frames){
        frames.getFrames().stream()
                .forEach(frame -> printFrame(frame));
    }

    private static void printFrame(Frame frame){
        if(STRIKE.equals(frame.findResult())){
            printStrike();
        }

        if(SPARE.equals(frame.findResult())){
            printSpare(frame);
        }

        if(GUTTER.equals(frame.findResult())){
            printGutter();
        }

        if(MISS.equals(frame.findResult())){
            printMiss(frame);
        }
    }

    private static void printStrike(){
        printMessage(BLANK_FIVE);
        printMessage(SYMBOL_STRIKE);
        printMessage(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printSpare(Frame frame){
        printMessage(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
        System.out.print(BLOCK_BORDER);
        System.out.print(DELIMITER_SPARE);
        printMessage(BLANK_THREE);
        printBlockBorder();
    }

    private static void printMiss(Frame frame){
        printMessage(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
        System.out.print(BLOCK_BORDER);
        System.out.print(frame.getPoints().getSecondPoint());
        printMessage(BLANK_THREE);
        printBlockBorder();
    }

    private static void printGutter(){
        printMessage(BLANK_FIVE);
        printMessage(SYMBOL_GUTTER);
        printMessage(BLANK_FOUR);
        printBlockBorder();
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
    }

    private static void printOneBlockWith(String message){
        printThreeBlankBlock();
        printMessage(message);
        printThreeBlankBlock();
        printBlockBorder();
    }

    private static void printMessage(String message){
        System.out.print(message);
    }

    private static String convertFrameNumberToString(int number){
        String stringNumber = (number >= 10) ? String.valueOf(" " + number + " ") : " 0"+ number + " ";
        return stringNumber;
    }

    private static void printThreeBlankBlock(){
        System.out.print(BLANK_THREE);
    }

    private static void printBlockBorder(){
        System.out.print(BLOCK_BORDER);
    }

    private static void printLineSeparator(){
        System.out.println();
    }
}
