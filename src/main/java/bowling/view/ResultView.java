package bowling.view;

import bowling.domain.FrameStrategy;
import bowling.domain.Frames;
import bowling.domain.Score;
import bowling.domain.User;

public class ResultView {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private static final int CALCULATION_NOT_COMPLETED = -1;
    private static final String NAME_LABEL = "| NAME |";
    private static final String EMPTY_FRAME = "      |";
    private static final String ROUND_LABEL_FRAME = "  |";
    private static final String EMPTY_SCORE = "";

    public void print(Frames frames) {
        printRoundInfo();

        System.out.print("|  " + frames.getUser().name() + " |");
        for (FrameStrategy frame : frames.getFrames()) {
            System.out.print("  ");
            printProceedingRound(frame);
        }

        printRemainingRound(frames.getFrames().size());
        printScore(frames);
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
        User user = frames.getUser();

        for (Score score : user.getScores().values()) {
            String frameScore = Integer.toString(score.score());
            frameScore = getFrameScore(frameScore);
            System.out.print(String.format("%4s", frameScore) + ROUND_LABEL_FRAME);
        }
        printRemainingRound(frames.getUser().scoreSize());
    }

    private String getFrameScore(String frameScore) {
        if (frameScore.equals(Integer.toString(CALCULATION_NOT_COMPLETED))) {
            frameScore = EMPTY_SCORE;
        }
        return frameScore;
    }
}
