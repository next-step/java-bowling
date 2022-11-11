package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingApplication {
    public static void main(String[] args) {
        List<String> names = InputView.inputPlayerNames();
        BowlingGame game = new BowlingGame(names);

        ResultView.printScoreBoard(game);

        while (!game.isEndGame()) {
            doGame(game);
        }
    }

    private static void doGame(BowlingGame game) {
        int inputDownPinCount;
        inputDownPinCount = InputView.inputDownPinCount(game.getPlayerName(0));
        game.doGame(inputDownPinCount);
        ResultView.printScoreBoard(game);
    }
}