import java.util.Objects;

public class PitchCount {
    private static final int MAX_PITCH_COUNT = 2;
    private static final int FINAL_MAX_PITCH_COUNT = 3;

    private final int count;

    public PitchCount(int count) {
        this.count = count;
    }

    public boolean isPitchable(boolean isFinalAndTwoStrike) {
        if (isFinalAndTwoStrike) {
            return count < FINAL_MAX_PITCH_COUNT;
        }
        return count < MAX_PITCH_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchCount that = (PitchCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
