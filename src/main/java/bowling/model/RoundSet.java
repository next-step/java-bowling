package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.controller.Main.bowlingResults;
import static bowling.model.Point.isValidRange;

public class RoundSet {
    private static final int FINAL_ROUND = 10;
    private static final int STRIKE_POINT = 10;

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

    public int play(int point) {
        int tryCount = rounds.size();
        int maxCount = 0;
        System.out.println("tryCount = " + tryCount);
        System.out.println(getLastRound() instanceof FinalRound);
        BowlingResult result = getLastRound().play(calcTotalPoint(point), tryCount);
        bowlingResults.add(result);

        boolean isSkip = isSkipNextRound();
        if (isSkip) {
            return -1;
        }

        boolean isBonus = giveBonus();
        System.out.println("give Bonus~~" +isBonus);
        if (isBonus) {
            maxCount = 1;
        }

        next(result);

        return maxCount;
    }

    private Round getLastRound() {
        return this.rounds.get(getSize() - 1);
    }

    private int getSize() {
        return rounds.size();
    }

    private int calcTotalPoint(int point) {
        if (point != STRIKE_POINT) {
            totalPoint += point;
            isValidRange(totalPoint);
            return totalPoint;
        }

        return STRIKE_POINT;
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
        System.out.println("rounds size = " + rounds.size());
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
