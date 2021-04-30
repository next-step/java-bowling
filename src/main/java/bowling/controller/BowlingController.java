package bowling.controller;

import bowling.entity.frame.Frame;
import bowling.entity.*;
import bowling.entity.frame.NormalFrame;
import bowling.view.ResultView;

import static bowling.view.InputView.*;
import static bowling.view.ResultView.*;

public class BowlingController {
    public static final int START_FRAME = 1;
    public static final int END_FRAME = 10;
    private static final String userName = userNameInput();

    public void start() {
        ResultView.bowlingGameStartPrint(userName);
        Frame bowlingFrame = new NormalFrame(START_FRAME);
        Frame bowlingPrintFrame = bowlingFrame;

        while (gameNotEnd(bowlingFrame)) {

            int frameNo = bowlingFrame.frameNo();
            bowlingFrame = bowlingFrame.pinResult(new Pin(framePithInput(frameNo)));
            BowlingBoard bowlingBoard = bowlingPrintFrame.bowlingBoard();

            bowlingFrameAndNamePrint(userName);
            normalFramePrint(bowlingBoard.boardResult());
            userBowlingFrameEmptyListPrint(frameNo);

        }
    }

    private boolean gameNotEnd(Frame bowlingFrame) {
        return !bowlingFrame.bowlingGameEnd();
    }

}
