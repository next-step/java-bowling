package bowling.view;

import bowling.domain.FrameStrategy;

import java.util.List;

public class ResultView {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private static final String NAME_LABEL = "| NAME |";
    private static final String EMPTY_FRAME = "      |";
    private static final String ROUND_LABEL_FRAME = "  |";

    public void print(String name, List<FrameStrategy> frames) {
        printRoundInfo();

        System.out.print("|  " + name + " |");
        for (FrameStrategy frame : frames) {
            System.out.print("  ");
            printProceedingRound(frame);
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
        ResultByPlayNumber.resultString(playNumber, frame);
    }

    private void printRemainingRound(int proceedingRound) {
        for (int thisFrame = proceedingRound + 1; thisFrame <= LAST_ROUND; thisFrame++) {
            System.out.print(EMPTY_FRAME);
        }
        System.out.println();
    }
}
