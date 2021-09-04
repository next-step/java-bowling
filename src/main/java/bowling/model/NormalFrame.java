package bowling.model;

public class NormalFrame {
    private static final int NUMBER_OF_SHOTS = 2;

    private final ShotResults shotResults = new ShotResults();

    public void record(ShotResult shotResult) {
        if (!isOver()) {
            shotResults.add(shotResult);
        }
    }

    public boolean isOver() {
        return shotResults.sumIsMax() || shotResults.size() == NUMBER_OF_SHOTS;
    }

    public FrameResult getResult() {
        return FrameResult.fromShotResults(shotResults);
    }
}
