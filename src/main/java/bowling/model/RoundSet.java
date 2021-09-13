package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.controller.Main.bowlingResults;
import static bowling.model.Point.isValidRange;

public class RoundSet {
    private static final int FINAL_ROUND = 10;
    private static final int SECOND_TRY = 2;
    private static final int FIRST_TRY = 1;

    private int totalPoint;
    private List<Round> rounds;

    public RoundSet(int index) {
        this.totalPoint = 0;
        this.rounds = new ArrayList<>();

        Round round = new NormalRound();
        if (index == FINAL_ROUND) {
            round = new FinalRound();
        }
        this.rounds.add(round);

    }

    public RoundSet(int totalPoint, List<Round> rounds) {
        this.totalPoint = totalPoint;
        this.rounds = rounds;
    }

    public int play(int tryCount, int point) {
        int count = tryCount;

        calcTotalPoint(point);
        BowlingResult result = getLastRound().play(totalPoint, tryCount);
        bowlingResults.add(result);

        boolean isSkip = isSkipNextRound();
        if (isSkip) {
            return SECOND_TRY;
        }

        boolean isBonus = giveBonus();
        if (isBonus && tryCount == FIRST_TRY) {
            count = 0;
        }

        if (isBonus && tryCount == SECOND_TRY) {
            count = FIRST_TRY;
            next(result);
        }

        if (tryCount == FIRST_TRY) {
            next(result);
        }

        return count;
    }

    private Round getLastRound() {
        return this.rounds.get(getSize() - 1);
    }

    private int getSize() {
        return rounds.size();
    }

    private void calcTotalPoint(int point) {
        if (isBonusRound()) {
            totalPoint = point;
        }

        if (!isBonusRound()) {
            totalPoint += point;
            isValidRange(totalPoint);
        }
    }

    private boolean isSkipNextRound() {
        if (getLastRound().isStrike()) {
            return true;
        }

        return false;
    }

    private void next(BowlingResult currentResult) {
        boolean isBonusRound = isBonusRound();

        if (!isBonusRound) {
            isBonusRound = giveBonus();
        }

        rounds.add(getLastRound().next(isBonusRound, currentResult));
    }

    private boolean isBonusRound() {
        return getLastRound().isBonusRound();
    }

    private boolean giveBonus() {
        return getLastRound().giveBonus();
    }

    public int size() {
        return rounds.size();
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
