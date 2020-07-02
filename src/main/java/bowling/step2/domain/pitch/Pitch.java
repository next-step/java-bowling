package bowling.step2.domain.pitch;

import java.util.Objects;

public abstract class Pitch {

    private static final int CLEAR_COUNT = 10;
    private static final int ALL_MISS = 0;
    private static final int MIN_PITCH = 0;
    private static final int MAX_PITCH = 10;
    private static final String PITCH_LIMIT_EXCEPTION = "투구시에는 0 ~ 10 사이의 값만 입력 가능합니다.";

    protected final int pitch;
    protected final PitchType pitchType;

    public Pitch(int pitch, PitchType pitchType) {
        validatePitch(pitch);
        this.pitch = pitch;
        this.pitchType = pitchType;
    }

    private void validatePitch(int pitch) {
        if (pitch > MAX_PITCH || pitch < MIN_PITCH){
            throw new IllegalArgumentException(PITCH_LIMIT_EXCEPTION);
        }
    }

    public static boolean isClear(int pitch){
        return CLEAR_COUNT == pitch;
    }

    public static boolean isAllMiss(int pitch){
        return ALL_MISS == pitch;
    }

    public abstract Pitch nextPitch(int pitch);

    public int getPitch() {
        return pitch;
    }

    public PitchType getPitchType() {
        return pitchType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch1 = (Pitch) o;
        return pitch == pitch1.pitch &&
                pitchType == pitch1.pitchType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitch, pitchType);
    }
}
