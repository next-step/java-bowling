package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingMain {
    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        BowlingGame game = new BowlingGame(playerName);

        OutputView.printEmptyScore(playerName);

        while (!game.isEnd()) {
            int count = InputView.inputFallCount(game.getFrameCount());
            game.pitch(count);
            OutputView.printScore(playerName, game.getFrames(), game.getScore());
        }
    }
}
