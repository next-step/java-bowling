package bowling.model;

public class NormalFrame implements Frame {
    private static final int NUMBER_OF_SHOTS = 2;

    ShotResults shotResults = new ShotResults();

    @Override
    public void record(ShotResult shotResult) {
        if (isOver()) {
            return;
        }
        shotResults.add(shotResult);
    }

    @Override
    public ShotResults getShotResults() {
        return shotResults;
    }

    @Override
    public boolean isOver() {
        return sumIsMax() || shotResults.size() == NUMBER_OF_SHOTS;
    }

    public boolean sumIsMax() {
        return shotResults.sumOfPinDown() == ShotResult.MAX.getNumOfPinDown();
    }
}
