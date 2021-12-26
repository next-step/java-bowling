package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private static final int NORMAL_PITCHING = 2;
    private static final int BONUS_PITCHING = 3;
    private static final int TOTAL_PINS = 10;
    private static final int SECOND_PITCHING = 2;
    private static final int ZERO_INDEX = 0;
    private static final String ERROR_TOTAL_PIN_VALUE_MSG = "핀의 총합은 10개입니다.";

    private int countOfPitching;
    private final List<BowlingPins> bowlingPins;

    public FinalFrame() {
        this.countOfPitching = NORMAL_PITCHING;
        this.bowlingPins = new ArrayList<>();
    }

    @Override
    public boolean isPossiblePitching() {
        if (this.countOfPitching == NORMAL_PITCHING) {
            return bowlingPins.size() < NORMAL_PITCHING;
        }
        return bowlingPins.size() < BONUS_PITCHING;
    }

    @Override
    public void addKnockDownPins(int hittingPins) {
        this.bowlingPins.add(new BowlingPins(hittingPins));
        if (isStrike(hittingPins) || isSpare()) {
            this.countOfPitching = BONUS_PITCHING;
        }
        if (bowlingPins.size() > 1) {
            checkHittingPins(hittingPins, bowlingPins.size() - 2);
        }
    }

    private void checkHittingPins(int hittingPins, int previous) {
        int totalPins = getPreviousHittingPins(previous) + hittingPins;
        if (getPreviousHittingPins(previous) != TOTAL_PINS && totalPins > TOTAL_PINS) {
            throw new IllegalArgumentException(ERROR_TOTAL_PIN_VALUE_MSG);
        }
    }

    private int getPreviousHittingPins(int previous) {
        return this.bowlingPins.get(previous).getCount();
    }

    private boolean isSpare() {
        if (bowlingPins.size() == SECOND_PITCHING) {
            return bowlingPins.stream()
                    .mapToInt(BowlingPins::getCount)
                    .sum() == TOTAL_PINS;
        }
        return false;
    }

    @Override
    public boolean isStrike(int hittingPins) {
        return hittingPins == TOTAL_PINS;
    }

    @Override
    public int getCountOfHits(int index) {
        return this.bowlingPins.get(index).getCount();
    }

    @Override
    public int getCountOfPitchingSize() {
        return this.bowlingPins.size();
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
        FinalFrame that = (FinalFrame) o;
        return countOfPitching == that.countOfPitching && Objects.equals(bowlingPins, that.bowlingPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPitching, bowlingPins);
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "countOfPitching=" + countOfPitching +
                ", bowlingPins=" + bowlingPins +
                '}';
    }

}
