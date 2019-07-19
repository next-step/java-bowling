package bowling;

import bowling.io.InputView;
import bowling.io.OutView;
import bowling.model.BowlingGame;
import bowling.model.BowlingGames;
import bowling.model.DownPin;
import bowling.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        int countOfPlayer = InputView.askCountOfPlayer();

        List<String> playerNames = InputView.askOfPlayerNames(countOfPlayer);
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .collect(Collectors.toList());

        BowlingGames bowlingGames = BowlingGames.settingOf(players);
        OutView.printProgress(bowlingGames.results());

        while (!bowlingGames.isGameOver()) {
            BowlingGame currentGame = bowlingGames.getCurrentGame();
            int countOfDownPins = InputView.askCountOfDownPins(currentGame.getPlayer());
            DownPin downPins = DownPin.valueOf(countOfDownPins);
            currentGame.play(downPins);
            OutView.printProgress(bowlingGames.results());
        }

        OutView.printGameOver();
    }
}
