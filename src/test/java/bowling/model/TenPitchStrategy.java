package bowling.model;

public class TenPitchStrategy implements PitchStrategy {
    public static final int TEN = 10;

    @Override
    public int nextPinDownCount(int leftPinCount) {
        return TEN;
    }
}
