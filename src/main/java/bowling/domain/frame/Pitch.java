package bowling.domain.frame;

import bowling.exception.OverScoreException;
import bowling.exception.PitchException;

import java.util.Objects;

public class Pitch {
    private static final int MIN_HITS = 0;
    private static final int MAX_HITS = 10;

    private final int pitch;

    public Pitch() {
        this.pitch = -1;
    }

    private Pitch(int pitch) {
        validatePitch(pitch);
        this.pitch = pitch;
    }

    public static Pitch from(int pitch) {
        return new Pitch(pitch);
    }

    private void validatePitch(int pitch) {
        if (pitch > MAX_HITS) {
            throw new PitchException(pitch);
        }
        if (pitch < MIN_HITS) {
            throw new PitchException(pitch);
        }
    }

    public boolean nonRecode() {
        return pitch == -1;
    }

    public boolean isStrike() {
        return pitch == 10;
    }

    public void checkTotal(Pitch second) {
        int totalScore = this.pitch + second.pitch;
        if (totalScore > 10) {
            throw new OverScoreException(totalScore);
        }
    }

    public boolean isSpare(Pitch second) {
        return this.pitch + second.pitch == 10;
    }

    public int getPitch() {
        return pitch;
    }

    public String score(Pitch second) {
        if ((this.pitch + second.pitch) == 10) {
            return String.format("  %d|/ |", this.pitch);
        }
        if (second.pitch == 0) {
            return String.format(" %d|-| ", this.pitch);
        }
        if (second.pitch == -1) {
            return String.format("  %d|  |", this.pitch);
        }

        return String.format("  %d|%d |", this.pitch, second.pitch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch1 = (Pitch) o;
        return pitch == pitch1.pitch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitch);
    }

}
