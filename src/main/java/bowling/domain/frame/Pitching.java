package bowling.domain.frame;

import java.util.Objects;

public class Pitching {
    public static final int STRIKE = 10;

    private final int first;
    private final int second;

    private Pitching(int first, int second) {
        validatePitching(first, second);
        this.first = first;
        this.second = second;
    }

    private void validatePitching(int first, int second) {
        PitchingValidation pitchingValidation = PitchingValidation.of(first, second);
        if (pitchingValidation != PitchingValidation.NONE) {
            throw new PitchingException(pitchingValidation.message());
        }
    }
    public static Pitching first(int first) {
        return new Pitching(first, 0);
    }

    public Pitching second(int second) {
        return new Pitching(this.first, second);
    }

    public boolean isStrike() {
        return first == STRIKE;
    }

    public boolean isSpare() {
        return !isStrike() && (first + second) == STRIKE;
    }

    public int sum() {
        return first + second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitching pitching = (Pitching) o;
        return first == pitching.first && second == pitching.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}
