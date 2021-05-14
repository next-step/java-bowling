package bowling.view;

import bowling.entity.frame.FrameResult;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.controller.BowlingController.END_FRAME;
import static bowling.controller.BowlingController.START_FRAME;

public class ResultView {
    private static final Character GUTTER_SYMBOL = '-';
    private static final String PRINT_NAME_FORMAT = "|%6s |";
    private static final String PRINT_FORMAT = "%6s |";
    private static final String FRAME_FORMAT = "%02d";
    private static final String EMPTY_STRING = "";
    private static final int NON_START_FRAME = 0;

    public ResultView() {
    }

    public void bowlingGameStartPrint(String userName) {
        bowlingFrameListPrint();
        userNamePrint(userName);
        userBowlingFrameEmptyListPrint(NON_START_FRAME);
    }

    public void bowlingFrameAndNamePrint(String userName) {
        bowlingFrameListPrint();
        userNamePrint(userName);
    }

    public void bowlingFrameListPrint() {
        String name = "NAME";
        userNamePrint(name);

        IntStream.rangeClosed(START_FRAME, END_FRAME).forEach(frame -> System.out.printf(PRINT_FORMAT, String.format(FRAME_FORMAT, frame)));

        System.out.println();
    }

    public void userNamePrint(String userName) {
        System.out.printf(PRINT_NAME_FORMAT, userName);
    }

    public void framePrint(List<FrameResult> bowlingResults) {
        for (FrameResult bowlingResult : bowlingResults) {
            System.out.printf(PRINT_FORMAT, bowlingResult.bowlingScore().replace('0', GUTTER_SYMBOL));
        }
    }

    public void frameScorePrint(List<FrameResult> bowlingResults) {
        userNamePrint("");

        for (FrameResult bowlingResult : bowlingResults) {
            int score = bowlingResult.totalScore();

            emptyFramePrint(score);

            if (score != -1) {
                System.out.printf(PRINT_FORMAT, score);
            }

        }
    }

    private void emptyFramePrint(int score) {
        if (score == -1) {
            System.out.printf(PRINT_FORMAT, "");
        }
    }

    public void userBowlingFrameEmptyListPrint(int printStartFrame) {
        for (int i = printStartFrame; i <= END_FRAME - 1; i++) {
            System.out.printf(PRINT_FORMAT, EMPTY_STRING);
        }
        System.out.println();
    }
}
