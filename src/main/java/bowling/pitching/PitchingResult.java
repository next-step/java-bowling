package bowling.pitching;

import bowling.global.exception.CanNotPitchingException;

import java.util.Objects;

public class PitchingResult {

    private static final int CLEAR_PINS = 0;
    private static final int PITCHING_COUNT_MAX = 2;


    private Pitching pitching;
    private String pitchResultState;
    private int remainingPins;

    private PitchingResult(Pitching pitching, int remainingPins, int pitchingMaxCount) {
        this.pitching = pitching;
        this.remainingPins = remainingPins;
        this.pitchResultState = pitchResultState(pitching, pitchingMaxCount); // 결과를 받아온다.
    }

    public static PitchingResult from(Pitching pitching, int remainingPins, int pitchingMaxCount) { // 투구를 했고, 결과를 받으려고 한다.
        return new PitchingResult(pitching, remainingPins, pitchingMaxCount);
    }

    public String pitchResultState(Pitching pitching, int pitchingMaxCount) {
        pitching.increasePitchingCount();

        this.remainingPins -= pitching.getPitchingPoint();

        validatePitchingCount(pitching, pitchingMaxCount);

        if (isRemainingPinsClear() && pitching.isFirstPitchingCount()) {
            return PitchingState.STRIKE.toString();
        }

        if (isRemainingPinsClear()) {
            return PitchingState.SPARE.toString();
        }

        if (pitching.getPitchingPoint() == CLEAR_PINS) {
            return PitchingState.GUTTER.toString();
        }
        return String.valueOf(pitching.getPitchingPoint());
    }

    private boolean isRemainingPinsClear() {
        return remainingPins == CLEAR_PINS;
    }

    public void validatePitchingCount(Pitching pitching, int pitchingCountLimit) {
        if (!pitching.canPitch(pitchingCountLimit)) {
            throw new CanNotPitchingException(String.format("투구는 %d회만 가능합니다.", pitchingCountLimit));
        }
    }

    public String getPitchResultState() {
        return pitchResultState;
    }

    public int getPitchingCount() {
        return pitching.getPitchingCount();
    }

    public int getRemainingPins() {
        return remainingPins;
    }

    public boolean getFirstPitchingCount() {
        return pitching.getPitchingCount() == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchingResult that = (PitchingResult) o;
        return Objects.equals(pitching, that.pitching) &&
                Objects.equals(pitchResultState, that.pitchResultState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitching, pitchResultState);
    }

    @Override
    public String toString() {
        return "PitchingResult{" +
                "pitching=" + pitching +
                ", pitchResultState='" + pitchResultState + '\'' +
                ", remainingPins=" + remainingPins +
                '}';
    }
}
