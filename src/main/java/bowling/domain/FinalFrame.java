package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {

    private static final int NORMAL_PITCHING = 2;
    private static final int BONUS_PITCHING = 3;
    private static final int TOTAL_PINS = 10;
    private static final String ERROR_TOTAL_PIN_VALUE_MSG = "핀의 총합은 10개입니다.";

    private int pitching;
    private final TotalHittingPins totalPins;

    public FinalFrame() {
        this.pitching = NORMAL_PITCHING;
        this.totalPins = new TotalHittingPins();
    }

    @Override
    public void pitching(int hittingPins) {
        HittingPins pins = new HittingPins(hittingPins);
        this.totalPins.addPins(pins);
        if (pins.isStrike() || isSpare()) {
            this.pitching = BONUS_PITCHING;
        }
        if (getCountOfPitching() == NORMAL_PITCHING) {
            checkNormalPitching();
        }
        if (getCountOfPitching() == BONUS_PITCHING) {
            checkBonusPitching();
        }
    }

    private boolean isSpare() {
        if (getCountOfPitching() == NORMAL_PITCHING) {
            return totalPins.isSpare();
        }
        return false;
    }

    private void checkNormalPitching() {
        int first = this.totalPins.getFirstPins();
        int second = this.totalPins.getSecondPins();
        if (first != TOTAL_PINS && first + second > TOTAL_PINS) {
            throw new IllegalArgumentException(ERROR_TOTAL_PIN_VALUE_MSG);
        }
    }

    private void checkBonusPitching() {
        int first = this.totalPins.getFirstPins();
        int second = this.totalPins.getSecondPins();
        int third = this.totalPins.getThirdPins();
        if ((first == TOTAL_PINS && second != TOTAL_PINS && second + third > TOTAL_PINS)) {
            throw new IllegalArgumentException(ERROR_TOTAL_PIN_VALUE_MSG);
        }
    }

    @Override
    public boolean isPossiblePitching() {
        if (this.totalPins.isTwoStrike()) {
            return false;
        }
        if (this.pitching == NORMAL_PITCHING) {
            return getCountOfPitching() < NORMAL_PITCHING;
        }
        return getCountOfPitching() < BONUS_PITCHING;
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
        FinalFrame that = (FinalFrame) o;
        return pitching == that.pitching && Objects.equals(totalPins, that.totalPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitching, totalPins);
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "pitching=" + pitching +
                ", totalPins=" + totalPins +
                '}';
    }

}
