package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGameResults {
    private final List<BowlingGameResult> bowlingGameResultList;

    public BowlingGameResults(List<BowlingGameResult> bowlingGameResultList) {
        this.bowlingGameResultList = new ArrayList<>(bowlingGameResultList);
    }

    public static BowlingGameResults of(List<BowlingGameResult> bowlingGameResultList) {
        return new BowlingGameResults(bowlingGameResultList);
    }

    public int size() {
        return this.bowlingGameResultList.size();
    }

    public BowlingGameResults add(BowlingGameResult bowlingGameResult) {
        List<BowlingGameResult> temp = new ArrayList<>(this.bowlingGameResultList);
        temp.add(bowlingGameResult);

        return new BowlingGameResults(temp);
    }

    public BowlingGameResults updateCurrentFrame(BowlingGameResult bowlingGameResult) {
        List<BowlingGameResult> temp = new ArrayList<>(this.bowlingGameResultList);
        temp.set(temp.size() - 1, bowlingGameResult);

        return new BowlingGameResults(temp);
    }

    public BowlingGameResults applyBonusToPreviousFrame(FrameScore bonusScore) {
        List<BowlingGameResult> temp = new ArrayList<>(this.bowlingGameResultList);
        BowlingGameResult bowlingGameResult = temp.get(temp.size() - 2);
        temp.set(temp.size() - 2, bowlingGameResult.applyBonus(bonusScore));

        return new BowlingGameResults(temp);
    }

    public BowlingGameResults applyBonusToTwoFramesAgo(FrameScore bonusScore) {
        List<BowlingGameResult> temp = new ArrayList<>(this.bowlingGameResultList);
        BowlingGameResult bowlingGameResult = temp.get(temp.size() - 3);
        temp.set(temp.size() - 3, bowlingGameResult.applyBonus(bonusScore));

        return new BowlingGameResults(temp);
    }

    public List<BowlingGameResult> getValues() {
        return new ArrayList<>(this.bowlingGameResultList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGameResults that = (BowlingGameResults) o;
        return Objects.equals(bowlingGameResultList, that.bowlingGameResultList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingGameResultList);
    }

    @Override
    public String toString() {
        return "BowlingGameResults{" +
                "bowlingGameResultList=" + bowlingGameResultList +
                '}';
    }
}
