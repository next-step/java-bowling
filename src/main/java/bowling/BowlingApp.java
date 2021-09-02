package bowling;

import bowling.domain.BowlingGameManager;
import bowling.domain.PlayerName;
import bowling.view.InputView;

public class BowlingApp {
    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.requirePlayerName());


        BowlingGameManager bowlingGameManager = new BowlingGameManager(playerName.getName());
        bowlingGameManager.run();
    }
}
