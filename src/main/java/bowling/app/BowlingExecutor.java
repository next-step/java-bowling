package bowling.app;

import bowling.domain.Participants;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class BowlingExecutor {

    private BowlingExecutor() {

    }

    public static void execute(List<Player> players) {
        Participants participants = new Participants(players);
        OutputView.printBoard(participants);
        boolean isEnd = false;
        while (!isEnd) {
            int numberOfPins = InputView.getNumberOfPins(participants.getCurrentPlayer());
            isEnd = participants.bowl(numberOfPins);
            OutputView.printBoard(participants);
        }
    }

}
