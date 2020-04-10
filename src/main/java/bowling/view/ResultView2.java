package bowling.view;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.point.Ordinal;

import java.util.stream.IntStream;

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
        if (frame.isStrike()) {
            printFrameResultWhen(FIRST, playerName, frames, frame);
            printFrameResultWhen(THIRD, playerName, frames, frame);
        }

        if (!frame.isStrike()) {
            printFrameResultWhen(FIRST, playerName, frames, frame);
            printFrameResultWhen(SECOND, playerName, frames, frame);
            printFrameResultWhen(THIRD, playerName, frames, frame);
        }
    }

    private static void printFrameResultWhen(Ordinal ordinal, PlayerName playerName, Frames frames, Frame frame) {
        if (frame.containsOrdinal(ordinal)) {
            printFrameId(frame);
            print(" : ");
            println(frame.getPointAtOrdinal(ordinal));

            printPlayInformation();
            printName(playerName.getName());
            frames.getFrames()
                    .subList(0, frame.getFrameId() - 1)
                    .forEach(frame1 -> printFrame(frame1));

            printFrameByOrdinal(frame, ordinal);
            printThreeLineSeparators();
        }
    }

    private static void printFrame(Frame frame) {
        if (frame.isStrike()) {
            printFrameFirstWhenStrike();
        }

        if (frame.isSpare()) {
            printSpare(frame);
        }

        if (frame.isMiss()) {
            printFrameSecondWhenMiss(frame);
        }

        if (frame.isGutter()) {
            printGutter();
        }
    }

    private static void printFrameByOrdinal(Frame frame, Ordinal ordinal) {
        if (frame.isStrike()) {
            printStrikeByOrdinal(frame, ordinal);
        }

        if (frame.isSpare()) {
            printSpareByOrdinal(frame, ordinal);
        }

        if (frame.isMiss()) {
            printMissByOrdinal(frame, ordinal);
        }

        if (frame.isGutter()) {
            printGutter();
        }
    }

    private static void printStrikeByOrdinal(Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal)) {
            printFrameFirstWhenStrike();
        }

        if (THIRD.equals(ordinal)) {
            printFrameThirdWhenStrike(frame);
        }
    }

    private static void printFrameFirstWhenStrike() {
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

    private static void printSpareByOrdinal(Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal)) {
            printFrameFirstWhenSpare(frame);
        }

        if (SECOND.equals(ordinal)) {
            printFrameFirstWhenSpare(frame);
            printFrameSecondWhenSpare(frame);
        }

        if (THIRD.equals(ordinal)) {
            printFrameFirstWhenSpare(frame);
            printFrameSecondWhenSpare(frame);
            printFrameThirdWhenSpare(frame);
        }
    }

    private static void printSpare(Frame frame) {
        printFrameFirstWhenSpare(frame);
        printFrameSecondWhenSpare(frame);
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printFrameFirstWhenSpare(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getFirstPoint());
    }

    private static void printFrameSecondWhenSpare(Frame frame) {
        print(BLOCK_BORDER);
        print(DELIMITER_SPARE);
    }

    private static void printFrameThirdWhenSpare(Frame frame) {
        print(BLOCK_BORDER);
        System.out.print(frame.getThirdPoint());
        print(BLANK_ONE);
        printBlockBorder();
    }

    private static void printMissByOrdinal(Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal)) {
            printFrameFirstWhenMiss(frame);
        }

        if (SECOND.equals(ordinal)) {
            printFrameSecondWhenMiss(frame);
        }
    }

    private static void printFrameFirstWhenMiss(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getFirstPoint());
    }

    private static void printFrameSecondWhenMiss(Frame frame) {
        print(BLANK_FOUR);
        System.out.print(frame.getFirstPoint());
        print(BLOCK_BORDER);
        System.out.print(frame.getSecondPoint());
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printGutter() {
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

    private static String convertFrameNumberToString(int number) {
        String stringNumber
                = (number >= 10) ? String.valueOf(BLANK_ONE + number + BLANK_ONE) : " 0" + number + BLANK_ONE;
        return stringNumber;
    }

    private static void printBlockBorder() {
        System.out.print(BLOCK_BORDER);
    }

    private static void printThreeLineSeparators() {
        printLineSeparator();
        printLineSeparator();
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println();
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void println(int message) {
        System.out.println(message);
    }
}