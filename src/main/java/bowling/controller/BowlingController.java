package bowling.controller;

import bowling.entity.frame.Frame;
import bowling.entity.*;
import bowling.entity.frame.NormalFrame;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

import static bowling.view.InputView.*;
import static bowling.view.ResultView.*;

public class BowlingController {
    public static final int START_FRAME = 1;
    public static final int END_FRAME = 10;
    private static final String userName = userNameInput();

    public void start() {
        ResultView.bowlingGameStartPrint(userName);
        Frame bowlingFrame = new NormalFrame(1);
        Frame bowlingPrintFrame = bowlingFrame;

        while (!gameEndCheck(bowlingFrame)) {
            int frameNo = bowlingFrame.frameNo();
            Pin fallenPin = new Pin(framePithInput(frameNo));
            bowlingFrame = bowlingFrame.pinResult(fallenPin);
            BowlingBoard bowlingBoard = bowlingPrintFrame.bowlingBoard();

            bowlingFrameAndNamePrint(userName);
            normalFramePrint(bowlingBoard.boardResult());
            userBowlingFrameEmptyListPrint(frameNo);

        }
    }

    private boolean gameEndCheck(Frame bowlingFrame) {
        return bowlingFrame.bowlingGameEnd();
    }

}
