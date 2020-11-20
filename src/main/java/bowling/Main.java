package bowling;

import bowling.domain.game.Game2;
import bowling.domain.player.Player;
import bowling.domain.view.InputView;
import bowling.domain.view.ResultView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());

        Game2 game = Game2.start();
        ResultView resultView = new ResultView();

        while(!game.isGameOver()) {
            int frameIndex = game.getFrameIndex();
            int pin = InputView.inputRoll(frameIndex);
            game.roll(pin);
            resultView.printResult(player, game.getResults());
        }
    }
}
