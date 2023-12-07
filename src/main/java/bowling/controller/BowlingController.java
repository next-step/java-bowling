package bowling.controller;

import bowling.domain.Ball;
import bowling.domain.FrameBoard;
import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.function.Supplier;

public class BowlingController {
    private final InputView inputView;
    private final OutputView outputView;

    public BowlingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PlayerName playerName = inputView.inputPlayerName();
        FrameBoard frameBoard = FrameBoard.init(playerName);
        outputView.printBoard(frameBoard);

        playFrames(frameBoard);
    }

    private void playFrames(FrameBoard frameBoard) {
        int frameIndex = 0;

        while (!frameBoard.hasEmptyFrame()) {
            playFrame(frameBoard, frameIndex);
            frameIndex++;
        }

        playBonusBall(frameBoard);
    }

    private void playBonusBall(FrameBoard frameBoard) {
        playFrame(frameBoard, 9);
        if (frameBoard.needLastFrameBonus()) {
            Ball bonusBall = inputView.inputBall(9);
            frameBoard.applyBonusBall(bonusBall);
            outputView.printBoard(frameBoard);
        }
    }

    private void playFrame(FrameBoard frameBoard, int frameIndex) {
        frameIndex = frameBoard.getNextFrameIndes();
        Ball firstBall = inputView.inputBall(frameIndex);
        frameBoard.applyFirstBallOf(frameIndex, firstBall);
        outputView.printBoard(frameBoard);

        if (!firstBall.isStrike()) {
            Ball secondBall = inputView.inputBall(frameIndex);
            frameBoard.applySecondBallOf(frameIndex, secondBall);
            outputView.printBoard(frameBoard);
        }


    }

    private <T> T readWithRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(supplier);
        }
    }
}
