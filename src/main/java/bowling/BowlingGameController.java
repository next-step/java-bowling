package bowling;

import bowling.domain.BowlingGames;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameController {
    public static void main(String[] args) {
        List<Player> players = InputView.scanPlayers().stream().map(Player::new).collect(Collectors.toList());
        BowlingGames bowlingGames = BowlingGames.initialize(players);
        OutputView.printBowlingGamesResult(bowlingGames);
        while (bowlingGames.isRunning()) {
            playBowlingGames(bowlingGames);
            OutputView.printBowlingGamesResult(bowlingGames);
        }
    }

    private static void playBowlingGames(BowlingGames bowlingGames) {
        Pins hitPins = Pins.create(InputView.scanHitPins(bowlingGames.currentPlayerName()));
        bowlingGames.playBowlingGame(hitPins);
    }
}