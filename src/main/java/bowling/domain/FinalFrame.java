package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    public static final int POSSIBLE_COUNT_OF_PITCHING = 2;

    private int countOfPitching;
    private final List<BowlingPins> bowlingPins;

    public FinalFrame() {
        this.countOfPitching = POSSIBLE_COUNT_OF_PITCHING;
        bowlingPins = new ArrayList<>();
    }

    @Override
    public void addKnockDownPins(int hittingPins) {

    }

    @Override
    public boolean isStrike(int hittingPins) {
        return false;
    }

    @Override
    public boolean isPossiblePitching() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return countOfPitching == that.countOfPitching && Objects.equals(bowlingPins, that.bowlingPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPitching, bowlingPins);
    }

}
