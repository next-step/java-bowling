package bowling;

import bowling.domain.BowlingGame;
import bowling.ui.InputView;
import bowling.ui.ResultView;

import java.util.List;

public class BowlingMain {
    public static void main(String[] args) {
        String userName = InputView.inputPlayerName();
        BowlingGame bowlingGame = new BowlingGame();
        List<String> playResult = bowlingGame.play();

        ResultView.printRoundResult(userName, playResult);

    }
}
