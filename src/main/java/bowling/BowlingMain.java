package bowling;

import bowling.View.InputView;
import bowling.View.ResultView;
import bowling.domain.Game;
import bowling.domain.Player;

public class BowlingMain {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        String playerName = inputView.requestPlayerName();
        Player player = new Player(playerName);

        Game game = new Game(player);
        resultView.printFrame(game.frames(), player);
        while (!game.isOver()) {
            int score = inputView.requestPitch(game.nextFrame().order());
            game.pitch(score);
            resultView.printFrame(game.frames(), player);
        }

    }
}
