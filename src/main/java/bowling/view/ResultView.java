package bowling.view;

import bowling.entity.frame.Frame;
import bowling.entity.frame.NormalFrame;
import bowling.entity.score.ScoreType;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.controller.BowlingController.END_FRAME;
import static bowling.controller.BowlingController.START_FRAME;

public class ResultView {

    private static final String PRINT_NAME_FORMAT = "|%5s |";
    private static final String PRINT_FORMAT = "%4s |";

    private ResultView() {
    }

    public static void bowlingGameStartPrint(String userName) {
        ResultView.bowlingFrameListPrint();
        ResultView.userNamePrint(userName);
        ResultView.userBowlingFrameEmptyListPrint(START_FRAME);
    }

    public static void bowlingFrameAndNamePrint(String userName) {
        ResultView.bowlingFrameListPrint();
        ResultView.userNamePrint(userName);
    }

    public static void bowlingFrameListPrint() {
        String name = "NAME";
        userNamePrint(name);

        IntStream.rangeClosed(START_FRAME, END_FRAME).forEach(i -> System.out.printf(PRINT_FORMAT, String.format("%02d", i)));

        System.out.println();
    }

    public static void userNamePrint(String userName) {
        System.out.printf(PRINT_NAME_FORMAT, userName);
    }

    public static void normalFramePrint(List<Frame> normalFrames) {
        for (Frame normalFrame : normalFrames) {
            System.out.printf(PRINT_FORMAT, normalFrame.scoreResult());
        }
    }

    public static void normalFramePrint(List<Frame> normalFrames, Frame currentFrame) {
        for (Frame normalFrame : normalFrames) {
            System.out.printf(PRINT_FORMAT, normalFrame.scoreResult());
        }

        System.out.printf(PRINT_FORMAT, currentFrame.scoreResult());
    }


    public static void userBowlingFrameEmptyListPrint(int printStartFrame) {
        IntStream.rangeClosed(printStartFrame, END_FRAME - 1).forEach(i -> System.out.printf(PRINT_FORMAT, ""));

        System.out.println();
    }
}
