package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {

    public static final int STRIKE_PITCHING = 1;
    public static final int POSSIBLE_COUNT_OF_PITCHING = 2;
    public static final int TOTAL_PINS = 10;
    public static final String ERROR_TOTAL_PIN_VALUE_MSG = "핀의 총합은 10개입니다.";
    public static final int ZERO_INDEX = 0;

    private int countOfPitching;
    private final List<BowlingPins> bowlingPins;

    public NormalFrame() {
        this.bowlingPins = new ArrayList<>();
        this.countOfPitching = POSSIBLE_COUNT_OF_PITCHING;
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
        if (bowlingPins.size() == POSSIBLE_COUNT_OF_PITCHING && totalPins > TOTAL_PINS) {
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
        return bowlingPins.size() < POSSIBLE_COUNT_OF_PITCHING;
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
