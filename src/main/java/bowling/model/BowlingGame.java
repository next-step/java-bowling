package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private List<RoundSet> allRounds;

    public BowlingGame() {
        this.allRounds = new ArrayList<>();
        roundInit();
    }

    public BowlingGame(int index) {
        this.allRounds = new ArrayList<>();
        allRounds.add(new RoundSet(index));
    }


    private void roundInit() {
        allRounds.add(new RoundSet(allRounds.size() + 1));
    }

    public int play(int point) {
        int maxTryCount = getRoundSet().play(point);

        if (isSkipNextRound(maxTryCount) || getRoundSet().isLastRound()) {
            roundInit();
        }

        return maxTryCount;
    }

    private boolean isSkipNextRound(int maxTryCount) {
        return maxTryCount == -1;
    }

    private RoundSet getRoundSet() {
        return allRounds.get(allRounds.size() - 1);
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
