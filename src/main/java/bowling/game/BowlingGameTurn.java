package bowling.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class BowlingGameTurn {

    private final Queue<BowlingGame> games;

    private BowlingGameTurn(final Queue<BowlingGame> games) {
        this.games = new LinkedList<>(games);
    }

    public static BowlingGameTurn newInstance(final List<BowlingGame> games) {
        return new BowlingGameTurn(new LinkedList<>(games));
    }

    public BowlingGame getNextTurn() {
        BowlingGame bowlingGame = games.peek();

        if (!Objects.isNull(bowlingGame) && bowlingGame.isRecentFrameOver()) {
            games.poll();
            return games.peek();
        }

        return bowlingGame;
    }
}
