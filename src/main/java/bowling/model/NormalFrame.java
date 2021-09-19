package bowling.model;

public class NormalFrame implements Frame {
    private static final int NUMBER_OF_SHOTS = 2;

    ShotResults shotResults = new ShotResults();

    public ShotResults getShotResults() {
        return shotResults;
    }

    public boolean sumIsStrike() {
        return shotResults.sumOfPinDown() == ShotResult.MAX.getNumOfPinDown();
    }

    @Override
    public void record(ShotResult shotResult) {
        if (isOver()) {
            return;
        }
        shotResults.add(shotResult);
    }

    @Override
    public boolean isOver() {
        return sumIsStrike() || shotResults.size() == NUMBER_OF_SHOTS;
    }
}
