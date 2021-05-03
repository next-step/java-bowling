package bowling.controller;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.frame.Frame;
import bowling.entity.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public static final int START_FRAME = 1;
    public static final int END_FRAME = 10;
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    public void start() {
        String userName = inputView.userNameInput();
        resultView.bowlingGameStartPrint(userName);
        Frame bowlingFrame = new NormalFrame(START_FRAME);
        Frame bowlingPrintFrame = bowlingFrame;

        while (gameNotEnd(bowlingFrame)) {
            int frameNo = bowlingFrame.frameNo();

            bowlingFrame = bowl(bowlingFrame, inputView.frameBowlInput(frameNo));
            BowlingBoard bowlingBoard = bowlingPrintFrame.bowlingBoard();
            resultView.bowlingFrameAndNamePrint(userName);

            resultView.framePrint(bowlingBoard.boardResult());
            resultView.userBowlingFrameEmptyListPrint(frameNo);

            resultView.frameScorePrint(bowlingBoard.boardResult());
            resultView.userBowlingFrameEmptyListPrint(frameNo);
        }
    }

    public Frame bowl(Frame bowlingFrame, int bowlingPin) {
        return bowlingFrame.bowl(new Pin(bowlingPin));
    }

    private boolean gameNotEnd(Frame bowlingFrame) {
        return !bowlingFrame.bowlingGameEnd();
    }

}
