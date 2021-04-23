package bowling.domain.pitch;

import java.util.Objects;

public class FinalPitchNumber implements PitchNumber {
    private static final int BONUS_PITCH_NUMBER = 3;
    private static final int LAST_PITCH_NUMBER = 2;
    private static final int FIRST_PITCH = 0;

    private final int pitchNumber;

    private FinalPitchNumber(int pitchNumber) {
        validate(pitchNumber);
        this.pitchNumber = pitchNumber;
    }

    public static FinalPitchNumber of(int pitchNumber) {
        return new FinalPitchNumber(pitchNumber);
    }

    public static FinalPitchNumber first() {
        return new FinalPitchNumber(FIRST_PITCH);
    }

    private void validate(int pitchNumber) {
        if (pitchNumber < FIRST_PITCH || pitchNumber > BONUS_PITCH_NUMBER) {
            throw new IllegalArgumentException("유효한 투구 시도 횟수가 아닙니다.");
        }
    }

    @Override
    public boolean isSecondPitch() {
        return this.pitchNumber > FIRST_PITCH
            && this.pitchNumber < LAST_PITCH_NUMBER;
    }

    @Override
    public int increase() {
        if (this.pitchNumber == BONUS_PITCH_NUMBER) {
            throw new IllegalStateException("보너스 투구 기회가 존재하지 않아 증가할수 없습니다.");
        }
        return this.pitchNumber + 1;
    }

    @Override
    public boolean isFirstPitch() {
        return this.pitchNumber == FIRST_PITCH;
    }

    @Override
    public boolean isLastPitch() {
        return this.pitchNumber >= LAST_PITCH_NUMBER;
    }

    @Override
    public boolean isBonusPitch() {
        return this.pitchNumber == BONUS_PITCH_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinalPitchNumber that = (FinalPitchNumber)o;
        return pitchNumber == that.pitchNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchNumber);
    }
}
