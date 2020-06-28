package bowling.view;

import bowling.application.BowlingGame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

public class BowlingGameController {

    private final BowlingGame bowlingGame;

    public BowlingGameController(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public void playGame() {
        Player player = InputView.askPlayer();
        Frames frames = bowlingGame.startGame();

        while (frames.canAddMoreScore()) {
            Score score = InputView.askScore(frames.getCurrentFrameIndex());
            frames = bowlingGame.addScore(frames, score);
            OutputView.printFrames(player, frames);
        }
    }
}
