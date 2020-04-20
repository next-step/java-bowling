package bowling.game;

import bowling.Pin;
import bowling.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGameBoard {

    private final BowlingGames bowlingGames;
    private BowlingGameTurn bowlingGameTurn;

    private BowlingGameBoard(final BowlingGames bowlingGames, final BowlingGameTurn bowlingGameTurn) {
        this.bowlingGames = bowlingGames;
        this.bowlingGameTurn = bowlingGameTurn;
    }

    public static BowlingGameBoard newInstance(final String... playerStrings) {
        List<Player> players = Arrays.stream(playerStrings)
                .map(Player::of)
                .collect(Collectors.toList());

        return newInstance(players);
    }

    public static BowlingGameBoard newInstance(final List<Player> players) {
        BowlingGames bowlingGames = BowlingGames.newInstance(players);
        BowlingGameTurn bowlingGameTurn = BowlingGameTurn.newInstance(bowlingGames.getAllGames());

        return new BowlingGameBoard(bowlingGames, bowlingGameTurn);
    }

    public void bowl(final Player player, Pin pinCount) {
        bowlingGames.bowl(player, pinCount);
    }

    public BowlingGame getNextTurn() {
        BowlingGame bowlingGame = bowlingGameTurn.getNextTurn();

        if (Objects.isNull(bowlingGame)) {
            reset();
            return bowlingGameTurn.getNextTurn();
        }

        return bowlingGame;
    }

    private void reset() {
        bowlingGames.prepareNextFrame();
        bowlingGameTurn = BowlingGameTurn.newInstance(bowlingGames.getAllGames());
    }

    public boolean isAllGameOver() {
        return bowlingGames.isAllGameOver();
    }
}
