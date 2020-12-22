package bowling.domain;

import java.util.Objects;

public class PitchResult {

    private final int BOWLING_PIN_COUNT = 10;
    private int pinCount;

    private PitchResult(int pinCount){
        validatePitchResult(pinCount);
        this.pinCount = pinCount;
    }

    private void validatePitchResult(int pinCount) {
        if ((pinCount > BOWLING_PIN_COUNT) || (pinCount < 0)) {
            throw new IllegalArgumentException("투구 결과는 총 볼링핀의 갯수를 넘을 수 없습니다.");
        }
    }

    public static PitchResult from(int pinCount){
        return new PitchResult(pinCount);
    }

    public int getPinCount() {
        return pinCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchResult that = (PitchResult) o;
        return pinCount == that.pinCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount);
    }
}
