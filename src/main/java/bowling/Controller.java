package bowling;

import bowling.domain.Game;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static void main(String[] args) {
        String player = InputView.inputNameOfPlayer();
        Game game = new Game();

        ResultView.printLabel();
        ResultView.printScore(player, game.getGameRecords());
    }
}
