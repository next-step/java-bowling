package bowling;

import bowling.domain.Game;
import bowling.domain.NormalFrame;
import bowling.domain.PlayBowling;
import bowling.view.InputView;

public class BowlingController {
    public static void main(String[] args) {
        String userName = InputView.enterUserName();
        Game game = new Game(userName);
        NormalFrame frame = game.startGame();
        PlayBowling.play(game, frame);
    }
}
