package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.player.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameRun {

    public static void main(String[] args) {
        Player player = InputView.inputPlayers();
        BowlingGame bowlingGame = new BowlingGame(player);

        Frames frames = bowlingGame.getFrames();
        Frame frame = bowlingGame.getFirstFrame();
        while (!frame.isLast()) {
            int frameNo = frames.getFrameNo(frame);
            Point point = InputView.inputThrowCount(frameNo);
            frame = bowlingGame.throwBall(frame, point);
            ResultView.viewResult(frames);
        }
    }
}
