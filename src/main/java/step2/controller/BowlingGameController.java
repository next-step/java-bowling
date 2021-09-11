package step2.controller;

import step2.domain.BowLingScoreViewFormat;
import step2.domain.Frames;
import step2.domain.UserName;
import step2.view.InputView;
import step2.view.ResultView;

public class BowlingGameController {
    private static final int FRAME_NUMBER_START = 1;
    private static final int FRAME_NUMBER_END = 10;

    private final UserName userName;

    public BowlingGameController(String userName) {
        this.userName = new UserName(userName);
    }

    public void run() {
        Frames frames = new Frames();
        for (int frameNumber = FRAME_NUMBER_START; frameNumber < FRAME_NUMBER_END; frameNumber++) {
            frames.startNormalFrame();
            playGameByFrame(frames, frameNumber);
        }
        frames.startFinalFrame();
        playGameByFrame(frames, FRAME_NUMBER_END);
    }

    private void playGameByFrame(Frames frames, int frameNumber) {
        while (!frames.isFinish(frameNumber)) {
            frames.throwBall(InputView.requireThrowBallNum(frameNumber));
            printBowlingGameResult(frames, frameNumber);
        }
    }

    private void printBowlingGameResult(Frames frames, int frameNumber) {
        ResultView.printFramesColumnName();
        ResultView.printUserName(userName.name());
        printBowlingGameResultByFrame(frames, frameNumber);
        ResultView.printEmptyResult(frameNumber);
    }

    private void printBowlingGameResultByFrame(Frames frames, int frameNumber) {
        for (int eachFrame = 1; eachFrame <= frameNumber; eachFrame++) {
            ResultView.printFramesResult(BowLingScoreViewFormat.transferBowlingScoreViewFormat(frames.frameScoreInfo(eachFrame)));
        }
    }
}
