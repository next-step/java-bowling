package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameRun {

    public static void main(String[] args) {
        Player player = InputView.inputPlayers();
        BowlingGame bowlingGame = new BowlingGame(player);

        Frame frame = bowlingGame.next();
        while (frame != null) {
            throwBallResult(frame);
            ResultView.viewResult(bowlingGame.getFrames());
            frame = bowlingGame.next();
        }
    }

    private static void throwBallResult(Frame frame) {
        try {
            int throwCount = InputView.inputThrowCount(frame);
            frame.throwBall(throwCount);
        } catch (IllegalArgumentException e) {
            ResultView.viewRetry(e);
        }
    }
}
