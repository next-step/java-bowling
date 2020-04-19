package bowling;

import bowling.domain.Game;
import bowling.domain.NormalFrame;
import bowling.domain.PlayBowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public static void main(String[] args) {
        String userName = InputView.enterUserName();
        Game game = new Game(userName);
        ResultView.print(game);
        NormalFrame frame = game.startGame();
        PlayBowling.play(game, frame);
    }
}
