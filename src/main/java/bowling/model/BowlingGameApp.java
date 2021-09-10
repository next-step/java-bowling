package bowling.model;

import bowling.model.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameApp {
    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        ResultView.printBoard(playerName);
        BowlingGame bowlingGame = new BowlingGame(playerName);

        while (bowlingGame.canPlayNext()) {
            int score = InputView.inputScore(bowlingGame.nextFrameNumber());
            List<Frame> frames = bowlingGame.play(score);
            ResultView.printFrames(playerName, frames);
        }
    }
}
