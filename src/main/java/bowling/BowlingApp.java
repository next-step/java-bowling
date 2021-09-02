package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApp {
    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.requirePlayerName());


        BowlingGame bowlingGame = new BowlingGame(playerName.getName());
        bowlingGame.run();
    }
}
