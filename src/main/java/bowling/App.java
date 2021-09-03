package bowling;

import bowling.domain.pins.Pins;
import bowling.domain.player.BowlingPlayerBoard;
import bowling.domain.player.BowlingPlayerBoards;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class App {

    public static void main(String[] args) {

        int inputPlayerCount = InputView.getInputPlayerCount();
        List<String> inputPlayerNames = InputView.getInputPlayerNames(inputPlayerCount);
        BowlingPlayerBoards bowlingPlayerBoards = BowlingPlayerBoards.of(inputPlayerNames);
        ResultView.printBoard(bowlingPlayerBoards);

        while (!bowlingPlayerBoards.isFinish()) {
            BowlingPlayerBoard currentPlayer = bowlingPlayerBoards.getCurrentPlayer();
            int pins = InputView.getInputPitch(currentPlayer.getPlayerName());
            bowlingPlayerBoards.bowl(Pins.of(pins));
            ResultView.printBoard(bowlingPlayerBoards);
        }
    }
}
