package bowling.pitching;

import bowling.global.exception.CanNotPitchingException;

import java.util.Objects;

public class PitchingResult {

    private static final int CLEAR_PINS = 0;
    private static final int PITCHING_COUNT_MAX = 2;
    private static final int INIT_PINS_NUMBER = 10;

    private final Pitching pitching;
    private final String pitchResultState;
    private int remainingPins;

    public PitchingResult(Pitching pitching) {
        this.pitching = pitching;
        this.remainingPins = INIT_PINS_NUMBER;
        this.pitchResultState = pitchResultState(pitching.getPitchingPoint());
    }

    public static PitchingResult from(Pitching pitching) {
        return new PitchingResult(pitching);
    }

    private String pitchResultState(int pitchPoint) {
        pitching.increasePitchingCount();
        remainingPins -= pitching.getPitchingPoint();

        validatePitchingCount(PITCHING_COUNT_MAX);

        if (isRemainingPinsClear() && pitching.isFirstPitchingCount()) {
            return PitchingState.STRIKE.toString();
        }

        if (isRemainingPinsClear()) {
            return PitchingState.SPARE.toString();
        }

        if (pitchPoint == CLEAR_PINS) {
            return PitchingState.GUTTER.toString();
        }
        return String.valueOf(pitchPoint);
    }

    private boolean isRemainingPinsClear() {
        return remainingPins == CLEAR_PINS;
    }

    public void validatePitchingCount(int pitchingCountLimit) {
        if (!pitching.canPitch(pitchingCountLimit)) {
            throw new CanNotPitchingException(String.format("투구는 %d회만 가능합니다.", pitchingCountLimit));
        }
    }

    public String getPitchResultState() {
        return pitchResultState;
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
        return "PitchResult{" +
                "pitching=" + pitching +
                ", pitchResultState='" + pitchResultState + '\'' +
                '}';
    }

}
