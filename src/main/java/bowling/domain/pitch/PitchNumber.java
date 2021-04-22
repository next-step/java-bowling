package bowling.domain.pitch;

public interface PitchNumber {
    int increase();
    boolean isFirstPitch();
    boolean isSecondPitch();
    boolean isLastPitch();
    boolean isBonusPitch();
}
