package bowling;

import bowling.domain.BowlingGameResult;

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
}
