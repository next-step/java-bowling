package bowling.view;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.point.Ordinal;

import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.*;
import static bowling.domain.point.Ordinal.*;

public class ResultView2 {
    private static final String BLANK_ONE = " ";
    private static final String BLANK_TWO = "  ";
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
                .forEach(frame -> printFrameResultSoFar(playerName, frames, frame));
    }

    private static void printFrameResultSoFar(PlayerName playerName, Frames frames, Frame frame) {
        if (frame.findResult().equals(STRIKE)) {
            printFrameResultFirst(playerName, frames, frame);
            printFrameResultThird(playerName, frames, frame);
        }

        if (!frame.findResult().equals(STRIKE)) {
            printFrameResultFirst(playerName, frames, frame);
            printFrameResultSecond(playerName, frames, frame);
            printFrameResultThird(playerName, frames, frame);
        }
    }

    private static void printFrameResultFirst(PlayerName playerName, Frames frames, Frame frame) {
        if (frame.getPoints().getPoints().keySet().contains(FIRST)) {
            printFrameId(frame);
            print(" : ");
            System.out.println(frame.getPoints().getFirstPoint());
            printPlayInformation();
            printName(playerName.getName());
            frames.getFrames()
                    .subList(0, frame.getFrameId() - 1)
                    .forEach(frame1 -> printFrame(frame1));
            printFrameOrdinal(frame, FIRST);
            printLineSeparator();
            printLineSeparator();
            printLineSeparator();
        }
    }

    private static void printFrameResultSecond(PlayerName playerName, Frames frames, Frame frame) {
        if (frame.getPoints().getPoints().keySet().contains(SECOND)) {
            printFrameId(frame);
            print(" : ");
            System.out.println(frame.getPoints().getSecondPoint());
            printPlayInformation();
            printName(playerName.getName());
            frames.getFrames()
                    .subList(0, frame.getFrameId() - 1)
                    .forEach(frame1 -> printFrame(frame1));
            printFrameOrdinal(frame, SECOND);

            printLineSeparator();
            printLineSeparator();
            printLineSeparator();
        }
    }

    private static void printFrameResultThird(PlayerName playerName, Frames frames, Frame frame) {
        if (frame.getPoints().getPoints().keySet().contains(THIRD)) {
            printFrameId(frame);
            print(" : ");
            System.out.println(frame.getPoints().getThirdPoint());
            printPlayInformation();
            printName(playerName.getName());
            frames.getFrames()
                    .subList(0, frame.getFrameId() - 1)
                    .forEach(frame1 -> printFrame(frame1));
            printFrameOrdinal(frame, THIRD);

            printLineSeparator();
            printLineSeparator();
            printLineSeparator();
        }
    }

    private static void printFrameOrdinal(Frame frame, Ordinal ordinal) {
        if (frame.findResult().equals(STRIKE)) {
            if (ordinal.equals(FIRST)) {
                printFrameFirstWhenStrike(frame);
            }

            if (ordinal.equals(THIRD)) {
                printFrameThirdWhenStrike(frame);
            }
        }

        if (frame.findResult().equals(SPARE)) {
            if (ordinal.equals(FIRST)) {
                printFrameFirstWhenSpare(frame);
            }

            if (ordinal.equals(SECOND)) {
                printFrameFirstWhenSpare(frame);
                printFrameSecondWhenSpare(frame);
            }

            if (ordinal.equals(THIRD)) {
                printFrameFirstWhenSpare(frame);
                printFrameSecondWhenSpare(frame);
                printFrameThirdWhenSpare(frame);
            }
        }

        if (frame.findResult().equals(MISS)) {
            if (ordinal.equals(FIRST)) {
                printFrameFirstWhenMiss(frame);
            }

            if (ordinal.equals(SECOND)) {
                printFrameSecondWhenMiss(frame);
            }
        }

        if (frame.findResult().equals(GUTTER)) {
            if (ordinal.equals(FIRST)) {

            }
        }
    }

    private static void printFrameFirstWhenSpare(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
    }

    private static void printFrameSecondWhenSpare(Frame frame) {
        print(BLOCK_BORDER);
        print(DELIMITER_SPARE);
    }

    private static void printFrameThirdWhenSpare(Frame frame) {
        print(BLOCK_BORDER);
        System.out.print(frame.getPoints().getThirdPoint());
        print(BLANK_ONE);
        printBlockBorder();
    }

    private static void printFrameFirstWhenMiss(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
    }

    private static void printFrameSecondWhenMiss(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getPoints().getFirstPoint());
        print(BLOCK_BORDER);
        System.out.print(frame.getPoints().getSecondPoint());
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printFrame(Frame frame) {
        if (STRIKE.equals(frame.findResult())) {
            printFrameFirstWhenStrike(frame);
        }

        if (SPARE.equals(frame.findResult())) {
            printSpare(frame);
        }

        if (GUTTER.equals(frame.findResult())) {
            printGutter(frame);
        }

        if (MISS.equals(frame.findResult())) {
            printMiss(frame);
        }
    }

    private static void printFrameFirstWhenStrike(Frame frame) {
        print(BLANK_FIVE);
        print(SYMBOL_STRIKE);
        print(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printFrameThirdWhenStrike(Frame frame) {
        print(BLANK_FIVE);
        print(SYMBOL_STRIKE);
        print(BLOCK_BORDER);
        System.out.print(frame.getPoints().getThirdPoint());
        print(BLANK_TWO);
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

    private static void printGutter(Frame frame) {
        print(BLANK_FIVE);
        print(SYMBOL_GUTTER);
        print(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printFrameId(Frame frame) {
        System.out.print(frame.getFrameId() + "프레임 투구");
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
        String stringNumber
                = (number >= 10) ? String.valueOf(BLANK_ONE + number + BLANK_ONE) : " 0" + number + BLANK_ONE;
        return stringNumber;
    }

    private static void printBlockBorder() {
        System.out.print(BLOCK_BORDER);
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}