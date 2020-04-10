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


    public static void print(PlayerName playerName, Frames frames) {
        printLineSeparator();
        printResult(playerName, frames);
    }

    private static void printResult(PlayerName playerName, Frames frames) {
        frames.getFrames().stream()
                .forEach(frame -> printPointResultSoFar(playerName, frames, frame));
    }

    private static void printPointResultSoFar(PlayerName playerName, Frames frames, Frame frame) {
        printPlayInformation();
        printName(playerName.getName());
        frames.getFrames()
                .subList(0, frame.getFrameId())
                .forEach(frame1 -> printFrame(frame1));
        printLineSeparator();
        printLineSeparator();
        printLineSeparator();
    }

    private static void printFrame(Frame frame) {
        if (STRIKE.equals(frame.findResult())) {
            printStrike();
        }

        if (SPARE.equals(frame.findResult())) {
            printSpare(frame);
        }

        if (GUTTER.equals(frame.findResult())) {
            printGutter();
        }

        if (MISS.equals(frame.findResult())) {
            printMiss(frame);
        }
    }

    private static void printStrike() {
        print(BLANK_FIVE);
        print(SYMBOL_STRIKE);
        print(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printSpare(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
        print(BLOCK_BORDER);
        print(DELIMITER_SPARE);
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printMiss(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
        print(BLOCK_BORDER);
        System.out.print(frame.getPoints().getSecondPoint());
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printGutter() {
        print(BLANK_FIVE);
        print(SYMBOL_GUTTER);
        print(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printPlayInformation() {
        printNameColumn(LABEL_NAME);
        IntStream.range(1, 11)
                .forEach(it -> printOneBlockWith(convertFrameNumberToString(it)));
        printLineSeparator();
    }

    private static void printNameColumn(String name) {
        printBlockBorder();
        printOneBlockWith(name);
    }

    private static void printName(String name) {
        printBlockBorder();
        print(BLANK_ONE);
        printOneBlockWith(name);
    }

    private static void printOneBlockWith(String message) {
        print(BLANK_THREE);
        print(message);
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static String convertFrameNumberToString(int number) {
        String stringNumber = (number >= 10) ? String.valueOf(" " + number + " ") : " 0" + number + " ";
        return stringNumber;
    }

    private static void printBlockBorder() {
        System.out.print(BLOCK_BORDER);
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}