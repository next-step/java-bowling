package bowling.model;

public class FourPitchStrategy implements PitchStrategy {
    public static final int FOUR = 4;

    @Override
    public int nextPinDownCount(int leftPinCount) {
        return FOUR;
    }
}

