package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void run() {
        Player player = Player.from(InputView.getPlayerName());
        Frames frames = Frames.init();
        OutputView.showScoreBoard(frames, player);
        while (frames.isContinue()) {
            int topplePin = InputView.getTopplePin(frames.round());
            frames.throwBall(topplePin);
            addScore(player, frames);
            OutputView.showScoreBoard(frames, player);
        }
    }

    private void addScore(Player player, Frames frames) {
        if (frames.canCalculateScore(player.scoreRound())) {
            player.addScore(frames.getScore(player.scoreRound()));
        }
    }
}
