package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {

    private static final int STRIKE_PITCHING = 1;
    private static final int NORMAL_PITCHING = 2;
    private static final int TOTAL_PINS = 10;
    private static final int ZERO_INDEX = 0;
    private static final String ERROR_TOTAL_PIN_VALUE_MSG = "핀의 총합은 10개입니다.";

    private int countOfPitching;
    private final List<BowlingPins> bowlingPins;

    public NormalFrame() {
        this.countOfPitching = NORMAL_PITCHING;
        this.bowlingPins = new ArrayList<>();
    }

    @Override
    public void addKnockDownPins(int hittingPins) {
        bowlingPins.add(new BowlingPins(hittingPins));
        if (isStrike(hittingPins)) {
            countOfPitching = STRIKE_PITCHING;
        }
        checkHittingPins(hittingPins);
    }

    private void checkHittingPins(int hittingPins) {
        int totalPins = getFirstHittingPins() + hittingPins;
        if (bowlingPins.size() == NORMAL_PITCHING && totalPins > TOTAL_PINS) {
            throw new IllegalArgumentException(ERROR_TOTAL_PIN_VALUE_MSG);
        }
    }

    private int getFirstHittingPins() {
        return this.bowlingPins.get(ZERO_INDEX).getCount();
    }

    @Override
    public boolean isStrike(int hittingPins) {
        return hittingPins == TOTAL_PINS;
    }

    @Override
    public boolean isPossiblePitching() {
        if (countOfPitching == STRIKE_PITCHING) {
            return false;
        }
        return bowlingPins.size() < NORMAL_PITCHING;
    }

    @Override
    public int getCountOfPitchingSize() {
        return this.bowlingPins.size();
    }

    @Override
    public int getCountOfHits(int index) {
        return this.bowlingPins.get(index).getCount();
    }

    public int getCountOfPitching() {
        return countOfPitching;
    }

    public List<BowlingPins> getBowlingPins() {
        return bowlingPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return countOfPitching == that.countOfPitching && Objects.equals(bowlingPins, that.bowlingPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPitching, bowlingPins);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "countOfPitching=" + countOfPitching +
                ", bowlingPins=" + bowlingPins +
                '}';
    }

}
