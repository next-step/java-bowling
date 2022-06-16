package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGames {

    private final List<BowlingGame> values;

    private BowlingGames(List<BowlingGame> values) {
        this.values = values;
    }

    public static BowlingGames initialize(List<Player> players) {
        List<BowlingGame> bowlingGames = new ArrayList<>();
        for (Player player : players) {
            bowlingGames.add(new BowlingGame(player));
        }
        return new BowlingGames(bowlingGames);
    }

    public void bowl(int hit, Player player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }
        bowlingGame(player).bowling(hit);
    }

    private BowlingGame bowlingGame(Player player) {
        return values.stream()
                .filter(bowlingGame -> bowlingGame.isSamePlayer(player))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Player currentPlayer() {
        if (!hasProgressingGame()) {
            return lowerFrameNoGamePlayer();
        }
        return progressingGamePlayer();
    }

    private boolean hasProgressingGame() {
        return values.stream()
                .filter(BowlingGame::isProgressing)
                .count() > 0L;
    }

    private Player lowerFrameNoGamePlayer() {
        return values.stream()
                .filter(bowlingGame -> !bowlingGame.isFinish())
                .min(BowlingGame::compareTo)
                .map(BowlingGame::player)
                .orElseThrow(IllegalStateException::new);
    }

    private Player progressingGamePlayer() {
        return values.stream()
                .filter(BowlingGame::isProgressing)
                .findFirst()
                .map(BowlingGame::player)
                .orElseThrow(IllegalStateException::new);
    }

    public boolean isFinish() {
        return values.stream()
                .allMatch(BowlingGame::isFinish);
    }

    public List<BowlingGame> values() {
        return Collections.unmodifiableList(values);
    }
}
