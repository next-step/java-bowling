package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.FrameScores;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = Frames.initiate();
        FrameScores frameScores = new FrameScores();
        OutputView.printDefaultScoreBoard(player);

        while (frames.hasNextTurn()) {
            Score score = Score.valueOf(InputView.inputPitch(frames));
            frames.bowl(score);
            OutputView.printScoreBoard(player, frames);
            frames.moveToNextFrame();
            OutputView.printNumericScore(frames);
        }
    }
}
