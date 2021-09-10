package bowling.model;

public class FinalFrame implements Frame {
    private static final int MAX_NUMBER_OF_SHOTS = 3;
    private static final int MIN_NUMBER_OF_SHOTS = 2;

    ShotResults shotResults = new ShotResults();

    @Override
    public void record(ShotResult shotResult) {
        if (isOver()) {
            return;
        }
        shotResults.add(shotResult);
    }

    @Override
    public boolean isOver() {
        return notGetBonusShot() || getBonusShot();
    }

    private boolean getBonusShot() {
        return shotResults.size() == MAX_NUMBER_OF_SHOTS;
    }

    private boolean notGetBonusShot() {
        return shotResults.size() == MIN_NUMBER_OF_SHOTS && shotResults.sumOfPinDown() < ShotResult.MAX.getNumOfPinDown();
    }

    @Override
    public String toString() {
        return shotResults.toString();
    }
}
