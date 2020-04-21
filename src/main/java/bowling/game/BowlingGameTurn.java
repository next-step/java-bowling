package bowling.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class BowlingGameTurn {

    private final Queue<BowlingGame> turns;

    private BowlingGameTurn(final Queue<BowlingGame> turns) {
        this.turns = new LinkedList<>(turns);
    }

    public static BowlingGameTurn newInstance(final List<BowlingGame> games) {
        return new BowlingGameTurn(new LinkedList<>(games));
    }

    public BowlingGame getNextTurn() {
        BowlingGame bowlingGame = turns.peek();

        if (!Objects.isNull(bowlingGame) && bowlingGame.isRecentFrameOver()) {
            turns.poll();
            return turns.peek();
        }

        return bowlingGame;
    }
}
