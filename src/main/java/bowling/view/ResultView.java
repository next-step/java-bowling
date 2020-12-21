package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Frames;

import java.util.stream.IntStream;

public class ResultView {

    private static final String BAR_DELIMITER = "|";
    private static final String NAME = "NAME";
    private static final int ONE = 1;
    private static final int FRAME_LENGTH = 11;
    private static final String EMPTY_SPACE = "      ";
    private static final String HALF_EMPTY_SPACE = "   ";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    private static StringBuilder SCORES = new StringBuilder();

    public static void printEmptyRecords(String name) {
        printSignature();
        printEmptyScore(name);
        System.out.println();
    }

    private static void printEmptyScore(String name) {
        printUsername(name);

        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(ResultView::printEmptyScore);
    }

    private static void printUsername(String name) {
        System.out.println();
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ", name));
        System.out.print(BAR_DELIMITER);
    }

    private static void printSignature() {
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ",NAME));
        System.out.print(BAR_DELIMITER);
        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(ResultView::printFrameNumbers);
    }

    private static void printFrameNumbers(int number) {
        System.out.print(String.format("  %02d  ", number));
        System.out.print(BAR_DELIMITER);
    }

    private static void printEmptyScore(int number) {
        System.out.print(EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);
    }

    public static void printFrame(String name, Frame frame, Frames frames) {
        printSignature();
        printUsername(name);
        System.out.print(SCORES);

        String firstSymbol = checkFirstSymbol(frame.getFirstScore());

        if(frame.getPitchSize() == 1) {
            printFirstPitch(frame, firstSymbol);
        }

        if(frame.getPitchSize() == 2) {
            printSecondPitch(frame, firstSymbol, frames);
        }

        if(frame.getPitchSize() == 3) {
            printThirdPitch(frame,firstSymbol);
            return;
        }

        IntStream.range(frames.getSize() + 2, FRAME_LENGTH)
                .forEach(ResultView::printEmptyScore);
        System.out.println();
    }

    private static void printFirstPitch(Frame frame, String firstSymbol) {
        System.out.print(String.format("%3s", firstSymbol));
        System.out.print(HALF_EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);

        if(frame.isFinish()) {
            SCORES.append(String.format("%3s", firstSymbol));
            SCORES.append(HALF_EMPTY_SPACE);
            SCORES.append(BAR_DELIMITER);
        }
    }

    private static void printSecondPitch(Frame frame, String firstSymbol, Frames frames) {
        String secondSymbol = checkSecondSymbol(frame.getSecondScore(), frame);
        System.out.print(String.format("%3s", firstSymbol));
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%s ", secondSymbol));
        System.out.print(BAR_DELIMITER);

        if(!frames.isFinalFrame()) {
            SCORES.append(String.format("%3s", firstSymbol));
            SCORES.append(BAR_DELIMITER);
            SCORES.append(String.format("%s ", secondSymbol));
            SCORES.append(BAR_DELIMITER);
        }
    }

    private static void printThirdPitch(Frame frame, String firstSymbol) {
        FinalFrame finalFrame = (FinalFrame) frame;

        String secondSymbol = checkFirstSymbol(frame.getSecondScore());
        String thirdSymbol = checkSecondSymbol(finalFrame.getThirdScore(), frame);
        System.out.print(String.format("%3s", firstSymbol));
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%s", secondSymbol));
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%s", thirdSymbol));
        System.out.print(BAR_DELIMITER);
    }

    private static String checkFirstSymbol(int score) {
        if(score == 0) {
            return GUTTER;
        }

        if(score == 10) {
            return STRIKE;
        }

        return "" + score;
    }

    private static String checkSecondSymbol(int score, Frame frame) {
        if(frame.isSpare()) {
            return SPARE;
        }

        return checkFirstSymbol(score);
    }

}
