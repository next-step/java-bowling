package bowling;

import bowling.domain.Game;
import bowling.domain.NormalFrame;
import bowling.domain.PlayBowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    private static InputView inputView = InputView.getInputView();
    private static ResultView resultView = ResultView.getResultView();
    private static PlayBowling playBowling = PlayBowling.getPlayBowling();

    public static void main(String[] args) {
        String userName = inputView.enterUserName();
        Game game = new Game(userName);
        resultView.print(game);
        NormalFrame frame = game.startGame();
        playBowling.play(game, frame);
    }
}
