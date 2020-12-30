package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.state.*;

import java.util.stream.IntStream;

public class ResultView {

    private static final String BAR_DELIMITER = "|";
    private static final String NAME = "NAME";
    private static final int ONE = 1;
    private static final int FRAME_LENGTH = 11;
    private static final String EMPTY_SPACE = "      ";
    private static final String HALF_EMPTY_SPACE = "   ";

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
        State state = frame.getState();
        printSignature();
        printUsername(name);
        System.out.print(SCORES);

        if(frames.isFinalFrame()) {
            printFinal(state, frame);
        }

        if(!frames.isFinalFrame()) {
            printFirstScore(state);
            printSecondScore(state);
        }

        IntStream.range(frames.getSize() + 2, FRAME_LENGTH)
                .forEach(ResultView::printEmptyScore);
        System.out.println();
    }

    private static void printFirstScore(State state) {
        if(!state.isFinish()) {
            System.out.print(String.format("%3s", state));
            System.out.print(HALF_EMPTY_SPACE);
            System.out.print(BAR_DELIMITER);
        }
    }

    private static void printSecondScore(State state) {
        if(state.isFinish() && !(state instanceof Strike)) {
            System.out.print(String.format("%5s ", state));
            System.out.print(BAR_DELIMITER);
            SCORES.append(String.format("%5s ", state));
            SCORES.append(BAR_DELIMITER);
        }

        if(state.isFinish() && (state instanceof Strike)) {
            System.out.print(String.format("%3s", state));
            System.out.print(HALF_EMPTY_SPACE);
            System.out.print(BAR_DELIMITER);
            SCORES.append(String.format("%3s", state));
            SCORES.append(HALF_EMPTY_SPACE);
            SCORES.append(BAR_DELIMITER);
        }
    }

    private static void printFinal(State state, Frame frame) {
        if(frame.getPitchSize() == 1) {
            printFinalFirst(state);
        }

        if(frame.getPitchSize() == 2) {
            printFinalSecond(state);
        }

        if(frame.getPitchSize() == 3) {
            printFinalThird(state);
        }
    }

    private static void printFinalFirst(State state) {
        if(state instanceof FinalStrike) {
            System.out.print(String.format("%3s", state));
            System.out.print(HALF_EMPTY_SPACE);
            System.out.print(BAR_DELIMITER);
            SCORES.append(String.format("%3s", state));
            SCORES.append(BAR_DELIMITER);
            return;
        }
        System.out.print(String.format("%3s", state));
        System.out.print(HALF_EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);
    }

    private static void printFinalSecond(State state) {
        if(state instanceof FinalStrike) {
            System.out.print(String.format("%s ", state));
            System.out.print(BAR_DELIMITER);
            SCORES.append(String.format("%s", state));
            SCORES.append(BAR_DELIMITER);
            return;
        }

        if(state instanceof FinalSpare) {
            System.out.print(String.format("%5s ", state));
            System.out.print(BAR_DELIMITER);
            SCORES.append(String.format("%5s", state));
            SCORES.append(BAR_DELIMITER);
            return;
        }

        if(state instanceof FinalMiss) {
            System.out.print(String.format("%5s ", state));
            System.out.print(BAR_DELIMITER);
            return;
        }

        System.out.print(String.format("%s ", state));
        System.out.print(BAR_DELIMITER);

    }

    private static void printFinalThird(State state) {
        if(state.isFinish() && (state instanceof FinalStrike)) {
            System.out.print(String.format("%s", state));
            System.out.print(BAR_DELIMITER);
            return;
        }

        if(state.isFinish() && !(state instanceof FinalStrike)) {
            System.out.print(String.format("%s", state));
            System.out.print(BAR_DELIMITER);
            return;
        }

        System.out.print(String.format("%s", state));
        System.out.print(BAR_DELIMITER);
        SCORES.append(String.format("%s", state));
        SCORES.append(BAR_DELIMITER);

    }
}
