package bowling;

import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingConsoleGame {

    public static void main(String[] args) {
        List<String> names = InputView.inputPlayerNames();
        BowlingGame bowlingGame = BowlingGame.init(names);

        ResultView.printScoreBoards(bowlingGame.playerDtos());

        while(!bowlingGame.isGameEnd()) {
            int downedPins = InputView.inputNumOfDownedPins(bowlingGame.currentPlayerDto());

            bowlingGame.play(downedPins);

            ResultView.printScoreBoards(bowlingGame.playerDtos());
        }
    }
}
