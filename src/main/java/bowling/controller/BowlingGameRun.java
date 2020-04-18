package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;
import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.domain.frame.OverThrowBallException;
import bowling.domain.point.PointOutOfRangeException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameRun {

    public static void main(String[] args) {
        Player player = InputView.inputPlayers();
        BowlingGame bowlingGame = new BowlingGame(player);

        Frames frames = bowlingGame.getFrames();
        Frame frame = frames.getFrame(0);
        int frameNo;
        while (!frame.isLast()) {
            frameNo = frames.getFrameNo(frame);
            throwBallResult(frameNo, frame);
            ResultView.viewResult(bowlingGame.getFrames());
            frame = bowlingGame.next();
        }
    }

    private static void throwBallResult(int frameNo, Frame frame) {
        try {
            int throwCount = InputView.inputThrowCount(frameNo);
            frame.throwBall(throwCount);
        } catch (OverThrowBallException | PointOutOfRangeException e) {
            ResultView.viewRetry(e);
        }
    }
}
