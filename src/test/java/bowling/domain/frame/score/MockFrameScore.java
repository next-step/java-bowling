package bowling.domain.frame.score;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MockFrameScore implements FrameScore {
    private final boolean isCalculated;
    private final int getScore;

    public MockFrameScore(boolean isCalculated, int getScore) {
        this.isCalculated = isCalculated;
        this.getScore = getScore;
    }


    @Override
    public boolean isCalculated() {
        return isCalculated;
    }

    @Override
    public int getScore() {
        return getScore;
    }

    @Override
    public void addBonus(int shot) {
        throw new NotImplementedException();
    }
}
