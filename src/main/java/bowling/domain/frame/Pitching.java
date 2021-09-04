package bowling.domain.frame;

import java.util.Objects;

public class Pitching {

    private final SinglePitching first;
    private final SinglePitching second;

    private Pitching(SinglePitching first, SinglePitching second) {
        validatePitching(first, second);
        this.first = first;
        this.second = second;
    }

    private void validatePitching(SinglePitching first, SinglePitching second) {
        PitchingValidation pitchingValidation = PitchingValidation.of(first, second);
        if (pitchingValidation != PitchingValidation.NONE) {
            throw new PitchingException(pitchingValidation.message());
        }
    }
    public static Pitching first(int first) {
        return new Pitching(new SinglePitching(first), null);
    }

    public Pitching second(int second) {
        return new Pitching(this.first, new SinglePitching(second));
    }

    public boolean isStrike() {
        return this.first.isStrike();
    }

    public boolean isSpare() {
        if (second == null) {
            return false;
        }
        return !isStrike() && second.isSpare(first);
    }

    public int sum() {
        return first.sum(second);
    }

    public boolean allPitched() {
        if (isStrike() || isSpare()) {
            return true;
        }
        return !isStrike() && second != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitching pitching = (Pitching) o;
        return Objects.equals(first, pitching.first) && Objects.equals(second, pitching.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
