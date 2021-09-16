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

        calcTotalPoint(point);

        GameResult result = getCurrentRound().play(totalPoint, tryCount);
        bowlingResults.add(result);

        if (totalPoint == STRIKE_POINT) {
            totalPoint = 0;
        }

        int maxTryCount = getCurrentRound().calcMaxTryCount();
        next(result);
        return maxTryCount;
    }

    private Round getCurrentRound() {
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

    private void next(GameResult currentResult) {
        rounds.add(getCurrentRound().next(currentResult));
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
