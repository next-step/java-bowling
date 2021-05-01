package bowling.view;

import bowling.domain.*;
import java.util.stream.IntStream;

public class ResultView {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private static final int CALCULATION_NOT_COMPLETED = -1;
    private static final String NAME_LABEL = "| NAME |";
    private static final String EMPTY_FRAME = "      |";
    private static final String ROUND_LABEL_FRAME = "  |";
    private static final String EMPTY_SCORE = "";

    public void print(Users users) {
        printRoundInfo();

        users.getUsers()
                .forEach(this::printScoreBoard);
    }

    private void printRoundInfo() {
        System.out.print(NAME_LABEL);
        for (int thisFrame = FIRST_ROUND; thisFrame <= LAST_ROUND; thisFrame++) {
            String frameNumber = String.format("%02d", thisFrame);
            System.out.print("  " + frameNumber + ROUND_LABEL_FRAME);
        }
        System.out.println();
    }

    private void printScoreBoard(User user, Frames frames) {
        System.out.print("|  " + user.name() + " |");
        for (FrameStrategy frame : frames.getFrames()) {
            System.out.print("  ");
            printProceedingRound(frame);
        }

        printRemainingRound(frames.frameSize());
        printScore(frames);
    }

    private void printProceedingRound(FrameStrategy frame) {
        int playNumber = frame.size();
        ResultByPlayNumber.printResult(frame, playNumber);
    }

    private void printRemainingRound(int proceedingRound) {
        for (int thisFrame = proceedingRound + 1; thisFrame <= LAST_ROUND; thisFrame++) {
            System.out.print(EMPTY_FRAME);
        }
        System.out.println();
    }

    private void printScore(Frames frames) {
        System.out.print("|      |");

        IntStream.range(0, frames.frameSize())
                .map(i -> frames.getScore(frames.frame(i)))
                .forEach(score -> System.out.print(String.format("%4s", getFrameScore(score)) + ROUND_LABEL_FRAME));

        printRemainingRound(frames.frameSize());
    }

    private String getFrameScore(int frameScore) {
        if (frameScore == CALCULATION_NOT_COMPLETED) {
            return EMPTY_SCORE;
        }
        return Integer.toString(frameScore);
    }
}
