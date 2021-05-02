package bowling.controller;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.Score;
import bowling.entity.frame.Frame;
import bowling.entity.frame.NormalFrame;
import bowling.view.ResultView;

import static bowling.view.InputView.frameBowlInput;
import static bowling.view.InputView.userNameInput;
import static bowling.view.ResultView.*;

public class BowlingController {
    public static final int START_FRAME = 1;
    public static final int END_FRAME = 10;
    public void start() {
        String userName = userNameInput();
        ResultView.bowlingGameStartPrint(userName);
        Frame bowlingFrame = new NormalFrame(START_FRAME);
        Frame bowlingPrintFrame = bowlingFrame;

        while (gameNotEnd(bowlingFrame)) {
            int frameNo = bowlingFrame.frameNo();

            bowlingFrame = bowl(bowlingFrame, frameBowlInput(frameNo));
            BowlingBoard bowlingBoard = bowlingPrintFrame.bowlingBoard();
            bowlingFrameAndNamePrint(userName);

            framePrint(bowlingBoard.boardResult());
            userBowlingFrameEmptyListPrint(frameNo);

            frameScorePrint(bowlingBoard.boardResult());
            userBowlingFrameEmptyListPrint(frameNo);
        }
    }

    public Frame bowl(Frame bowlingFrame, int bowlingPin) {
        return bowlingFrame.bowl(new Pin(bowlingPin));
    }

    private boolean gameNotEnd(Frame bowlingFrame) {
        return !bowlingFrame.bowlingGameEnd();
    }

}
