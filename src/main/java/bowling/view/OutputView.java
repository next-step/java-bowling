package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.Player;

import java.util.stream.IntStream;

public class OutputView {
    private static final String PIPE = "|";
    private static final String EMPTY_FRAME = "       ";
    private static final String SPACING_THREE = "   ";
    private static final String SPACING_TWO = "  ";
    private static final String SPACING_ONE = " ";
    private static final String FIRST_NUMBER_PLUS = "0";
    private static final String NEXT_LINE = "\n";
    private static final String PLAYER_NAME = "|  %s |";
    private static final String HEAD = head();
    private static final int MAX_FRAME = 10;
    private static final int MAX_PIN = 10;
    private static final int MIN_PIN = 0;

    private static String head() {
        StringBuilder round = new StringBuilder(PIPE + SPACING_ONE + "NAME" + SPACING_ONE + PIPE);
        for (int i = 1; i <= MAX_FRAME; i++) {
            round.append(SPACING_TWO).append(singleNumberFirstZero(i)).append(SPACING_THREE).append(PIPE);
        }
        return round.toString();
    }

    private static String singleNumberFirstZero(int number) {
        if (number < MAX_FRAME) {
            return FIRST_NUMBER_PLUS + number;
        }
        return String.valueOf(number);
    }

    public static void printScoreBoard(Player name) {
        System.out.println(HEAD);
        printName(name);
        printEmptyFrame(MAX_FRAME);
        System.out.print(NEXT_LINE);
    }

    private static void printName(Player name) {
        System.out.printf(PLAYER_NAME, name.value());
    }

    private static void printEmptyFrame(int loopNumber) {
        IntStream.range(0, loopNumber)
                .forEach(i -> System.out.print(EMPTY_FRAME + PIPE));
        System.out.print(NEXT_LINE);
    }

    public static void printFrame(Player name, Frames frames) {
        System.out.println(HEAD);
        printName(name);
        printScorePin(frames);
        printEmptyFrame(MAX_FRAME - frames.frameNumber());
        System.out.print(NEXT_LINE);
    }

    private static void printScorePin(Frames frames) {
        int loopNumber = frames.frameNumber();
        for (int i = 0; i < loopNumber; i++) {
            String pin = scorePin(frames.findFrame(i));
            spacing(pin);
            System.out.print(pin);
            spacing(pin);
            System.out.print(PIPE);
        }
    }

    private static String scorePin(Frame frame) {
        if (frame.numberOfBall() == 1) {
            return markScore(frame.fallenPin(0));
        }

        if (frame.numberOfBall() == 2) {
            return markScore(frame.fallenPin(0), frame.fallenPin(1));
        }

        return markScore(frame.fallenPin(0), frame.fallenPin(1), frame.fallenPin(2));
    }

    private static void spacing(String text) {
        if (text.length() == 1) {
            System.out.print(SPACING_THREE);
        }

        if (text.length() == 3) {
            System.out.print(SPACING_TWO);
        }

        if (text.length() == 5) {
            System.out.print(SPACING_ONE);
        }
    }

    private static String markScore(int first) {
        if (first == MAX_PIN) {
            return "X";
        }
        if (first == MIN_PIN) {
            return "-";
        }
        return String.valueOf(first);
    }

    private static String markScore(int first, int second) {
        if (first + second == MAX_PIN) {
            return first + "|" + "/";
        }
        return markScore(first) + "|" + markScore(second);
    }

    private static String markScore(int first, int second, int bonus) {
        if (first == MAX_PIN) {
            return "X|" + markScore(second, bonus);
        }
        return markScore(first, second) + "|" + markScore(bonus);
    }
}
