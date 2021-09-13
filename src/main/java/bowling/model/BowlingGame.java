package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private static final int MAX_ROUND = 10;

    private List<RoundSet> allRounds;

    public BowlingGame() {
        this.allRounds = new ArrayList<>();
        for (int i = 1; i <= MAX_ROUND; i++) {
            allRounds.add(new RoundSet(i));
        }
    }

    public BowlingGame(int index, RoundSet roundSet) {
        this();
        this.allRounds.add(index-1, roundSet);
        this.allRounds.remove(index);
    }

    public int play(int index, int tryCount, int point) {
        if (allRounds.size() < index){
            allRounds.add(new RoundSet(index));
        }

        return getRoundSet(index).play(tryCount, point);
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
