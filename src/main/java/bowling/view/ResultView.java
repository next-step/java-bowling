package bowling.view;

import bowling.domain.FrameStrategy;

import java.util.List;

public class ResultView {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private static final String NAME_LABEL = "| NAME |";
    private static final String BOUNDARY = "|";
    private static final String EMPTY_FRAME = "      |";
    private static final String SINGLE_FRAME = "   |";
    private static final String FULL_FRAME = " |";
    private static final String ROUND_LABEL_FRAME = "  |";

    public void print(String name, List<FrameStrategy> frames) {
        printRoundInfo();

        System.out.print("|  " + name + " |");
        for (FrameStrategy normalFrame : frames) {
            System.out.print("  ");
            printProceedingRound(normalFrame);
        }

        printRemainingRound(frames.size());
    }

    private void printRoundInfo() {
        System.out.print(NAME_LABEL);
        for (int thisFrame = FIRST_ROUND; thisFrame <= LAST_ROUND; thisFrame++) {
            String frameNumber = String.format("%02d", thisFrame);
            System.out.print("  " + frameNumber + ROUND_LABEL_FRAME);
        }
        System.out.println();
    }

    private void printProceedingRound(FrameStrategy frame) {
        int playNumber = frame.size();

        if (playNumber == 1) {
            printState(frame, 1);
            System.out.print(SINGLE_FRAME);
        }
        if (playNumber == 2) {
            printState(frame, 1);
            System.out.print(BOUNDARY);
            printState(frame, 2);
            System.out.print(FULL_FRAME);
        }
        if (playNumber == 3) {
            printState(frame, 1);
            System.out.print(BOUNDARY);
            printState(frame, 2);
            System.out.print(BOUNDARY);
            printState(frame, 3);
            System.out.println(FULL_FRAME);
        }
    }

    private void printState(FrameStrategy frame, int index) {
        String frameState = frame.result(index);
        System.out.print(frameState);
    }

    private void printRemainingRound(int proceedingRound) {
        for (int thisFrame = proceedingRound + 1; thisFrame <= LAST_ROUND; thisFrame++) {
            System.out.print(EMPTY_FRAME);
        }
        System.out.println();
    }
}
