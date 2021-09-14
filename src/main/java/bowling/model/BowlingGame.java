package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private static final int MAX_ROUND = 10;

    private List<RoundSet> allRounds;

    public BowlingGame() {
        this.allRounds = new ArrayList<>();
    }

    public BowlingGame(int index, RoundSet roundSet) {
        this();
        this.allRounds.add(index-1, roundSet);
        this.allRounds.remove(index);
    }

    public void roundInit() {
        allRounds.add(new RoundSet(allRounds.size() + 1));
    }

    public int play(int point) {
        return getRoundSet(allRounds.size()).play(point);
    }

    private RoundSet getRoundSet(int index) {
        return allRounds.get(index - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame game = (BowlingGame) o;
        return Objects.equals(allRounds, game.allRounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allRounds);
    }
}
