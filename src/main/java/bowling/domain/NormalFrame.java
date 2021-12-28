package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {

    private static final int STRIKE_PITCHING = 1;
    private static final int NORMAL_PITCHING = 2;
    private static final int TOTAL_PINS = 10;
    private static final String ERROR_TOTAL_PIN_VALUE_MSG = "핀의 총합은 10개입니다.";

    private int pitching;
    private final TotalHittingPins totalPins;

    public NormalFrame() {
        this.pitching = NORMAL_PITCHING;
        this.totalPins = new TotalHittingPins();
    }

    @Override
    public void pitching(int hittingPins) {
        HittingPins pins = new HittingPins(hittingPins);
        this.totalPins.addPins(pins);
        if (pins.isStrike()) {
            this.pitching = STRIKE_PITCHING;
        }
        if (getCountOfPitching() == NORMAL_PITCHING) {
            checkHittingPins();
        }
    }

    private void checkHittingPins() {
        int sumPins = this.totalPins.getFirstPins() + this.totalPins.getSecondPins();
        if (sumPins > TOTAL_PINS) {
            throw new IllegalArgumentException(ERROR_TOTAL_PIN_VALUE_MSG);
        }
    }

    @Override
    public boolean isPossiblePitching() {
        if (this.pitching == STRIKE_PITCHING) {
            return false;
        }
        return getCountOfPitching() < NORMAL_PITCHING;
    }

    @Override
    public int getCountOfHits(int pitchingNumber) {
        return this.totalPins.getCountOfHits(pitchingNumber);
    }

    @Override
    public int getCountOfPitching() {
        return this.totalPins.size();
    }

    public int getPitching() {
        return this.pitching;
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
        return pitching == that.pitching && Objects.equals(totalPins, that.totalPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitching, totalPins);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "pitching=" + pitching +
                ", totalPins=" + totalPins +
                '}';
    }

}
