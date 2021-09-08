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

    @Override
    public String toString() {
        FrameResult result = FrameResult.fromShotResults(shotResults);

        if (result == FrameResult.STRIKE) {
            return "X";
        }

        String shotResultString = shotResults.toString();
        if (result == FrameResult.SPARE) {
            StringBuilder sb = new StringBuilder(shotResultString);
            sb.setCharAt(shotResultString.length() - 1, '/');
            return sb.toString();
        }
        return shotResultString;
    }
}
