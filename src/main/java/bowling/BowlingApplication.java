package bowling;

import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        int playerNumber = InputView.inputPlayerNumber();
        List<UserName> userNames = InputView.inputUserNames(playerNumber);
        BowlingGames bowlingGames = BowlingGames.start(userNames);

        ResultView.printResult(bowlingGames);

        while (!bowlingGames.isFinished()) {
            int score = InputView.inputUserScore(bowlingGames.currentTurnOfUser().getUserName());
            bowlingGames.bowl(Pin.from(score));
            ResultView.printResult(bowlingGames);
        }
    }
}
