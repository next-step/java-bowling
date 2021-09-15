package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.controller.Main.bowlingResults;

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
        calcTotalPoint(point);
        GameResult result = getLastRound().play(totalPoint, tryCount);
        if (totalPoint == STRIKE_POINT) {
            totalPoint = 0;
        }
        bowlingResults.add(result);

        boolean isSkip = isSkipNextRound();
        if (isSkip) {
            return -1;
        }

        boolean isBonus = giveBonus();
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

    private void calcTotalPoint(int point) {
        if (point != STRIKE_POINT) {
            totalPoint += point;
        }

        if (point == STRIKE_POINT) {
            totalPoint = point;
        }
    }

    private boolean isSkipNextRound() {
        if (getLastRound().isStrike()) {
            return true;
        }

        return false;
    }

    private void next(GameResult currentResult) {
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
