package bowling;

import bowling.domain.BowlingClub;
import bowling.ui.InputView;
import bowling.ui.ResultView;

import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
        List<String> names = InputView.InputPlayers();
        BowlingClub bowlingClub = new BowlingClub(names);

        ResultView.printBowlingResult(bowlingClub.games());

        while (bowlingClub.isNotEnd()) {
            int fallenPin = InputView.nextFallenPin(bowlingClub.nameOfPlayerForThisTurn());
            bowlingClub.roll(fallenPin);
            ResultView.printBowlingResult(bowlingClub.games());
        }

    }

}
