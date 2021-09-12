package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.model.Point.isValidRange;

public class RoundSet {
    private int totalPoint;
    private List<Round> rounds;

    public RoundSet(int index) {
        this.totalPoint = 0;
        this.rounds = new ArrayList<>();

        if (index == 10) {
            this.rounds.add(new FinalRound());
        }else{
            this.rounds.add(new NormalRound());

        }
    }

    public RoundSet(int totalPoint, List<Round> rounds) {
        this.totalPoint = totalPoint;
        this.rounds = rounds;
    }

    public void nextRound() {
        rounds.add(getLastRound().next());
    }

    public boolean isBonus() {
        return getLastRound().isBonus();
    }

    public boolean isSkipNextRound() {
        return getLastRound().isSkipNextRound();
    }

    public BowlingResult play(int tryCount, BowlingResult beforeResult) {
        return getLastRound().play(totalPoint, tryCount, beforeResult);
    }


    private Round getLastRound() {
        return this.rounds.get(getSize() - 1);
    }

    private int getSize() {
        return rounds.size();
    }

    public int calcTotalPoint(boolean isBonusRound, int score) {
        if (isBonusRound) {
            totalPoint = score;
            return totalPoint;
        }

        totalPoint += score;
        isValidRange(totalPoint);

        return totalPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundSet roundSet = (RoundSet) o;
        return totalPoint == roundSet.totalPoint && Objects.equals(rounds, roundSet.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPoint, rounds);
    }
}
