package bowling;

import bowling.domain.bowlinggame.BowlingGames;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            playerNames.add(InputView.inputPlayerName(i));
        }
        OutputView.outputDefaultFrame(playerNames);

        BowlingGames bowlingGames = new BowlingGames(playerNames);
        while (!bowlingGames.isAllGameOver()) {
            Player player = bowlingGames.currentPlayer();
            int point = InputView.inputScore(player);
            bowlingGames.writePoint(point);
            OutputView.outputBowlingGames(bowlingGames);
        }
    }
}
