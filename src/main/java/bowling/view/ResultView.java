package bowling.view;

import bowling.entity.frame.FrameResult;
import bowling.entity.frame.NormalFrameResult;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.controller.BowlingController.END_FRAME;
import static bowling.controller.BowlingController.START_FRAME;

public class ResultView {

    private static final String PRINT_NAME_FORMAT = "|%6s |";
    private static final String PRINT_FORMAT = "%6s |";
    private static final String FRAME_FORMAT = "%02d";
    private static final String EMPTY_STRING = "";
    private static final int NON_START_FRAME = 0;

    private ResultView() {
    }

    public static void bowlingGameStartPrint(String userName) {
        ResultView.bowlingFrameListPrint();
        ResultView.userNamePrint(userName);
        ResultView.userBowlingFrameEmptyListPrint(NON_START_FRAME);
    }

    public static void bowlingFrameAndNamePrint(String userName) {
        ResultView.bowlingFrameListPrint();
        ResultView.userNamePrint(userName);
    }

    public static void bowlingFrameListPrint() {
        String name = "NAME";
        userNamePrint(name);

        IntStream.rangeClosed(START_FRAME, END_FRAME).forEach(frame -> System.out.printf(PRINT_FORMAT, String.format(FRAME_FORMAT, frame)));

        System.out.println();
    }

    public static void userNamePrint(String userName) {
        System.out.printf(PRINT_NAME_FORMAT, userName);
    }

    public static void normalFramePrint(List<FrameResult> bowlingResults) {
        for (FrameResult bowlingResult : bowlingResults) {
            System.out.printf(PRINT_FORMAT, bowlingResult.bowlingScore());
        }
    }

    public static void userBowlingFrameEmptyListPrint(int printStartFrame) {
        for (int i = printStartFrame; i <= END_FRAME - 1; i++) {
            System.out.printf(PRINT_FORMAT, EMPTY_STRING);
        }
        System.out.println();
    }
}
