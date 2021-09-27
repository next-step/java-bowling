package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.controller.Main.bowlingResults;

public class RoundSet {
    private static final int FINAL_ROUND = 10;
    private static final int STRIKE_POINT = 10;

    private Point point;
    private List<Round> rounds;

    public RoundSet(int index) {
        this.point = new Point(0);
        this.rounds = new ArrayList<>();

        Round round = new NormalRound();
        if (index == FINAL_ROUND) {
            round = new FinalRound();
        }
        this.rounds.add(round);

    }

    public RoundSet(int point, List<Round> rounds) {
        this.point = new Point(point);
        this.rounds = rounds;
    }

    public int size() {
        return rounds.size();
    }

    public int play(int pinCount) {
        int tryCount = rounds.size();

        this.point = calcTotalPoint(pinCount);

        State result = getCurrentRound().bowl(pinCount);
        List<Score> scores = getCurrentRound().calculateScore(pinCount);
        bowlingResults.add(result);

        if (this.point.isStrike()) {
            this.point = new Point(0);
        }

        int maxTryCount = getCurrentRound().calcMaxTryCount();
        next(result, scores);
        return maxTryCount;
    }

    private Point calcTotalPoint(int point) {
        if (point != STRIKE_POINT) {
            return this.point.add(point);
        }

        return new Point(point);
    }

    private void next(State state, List<Score> scores) {
        this.rounds.add(getCurrentRound().next(state, scores));
    }

    private Round getCurrentRound() {
        return this.rounds.get(size() - 1);
    }

    private boolean isNormalRound() {
        return getCurrentRound() instanceof NormalRound;
    }

    public boolean isLastRound() {
        if (isNormalRound() && ((NormalRound) getCurrentRound()).isLastRound(rounds.size())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundSet roundSet = (RoundSet) o;
        return Objects.equals(point, roundSet.point) && Objects.equals(rounds, roundSet.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, rounds);
    }
}
