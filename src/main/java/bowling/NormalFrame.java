package bowling;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {
    private static final int NUMBER_OF_SHOTS = 2;

    private final List<Integer> shots = new ArrayList<>();

    public void record(int shotResult) {
        if (!isOver()) {
            shots.add(shotResult);
        }
    }

    public boolean isOver() {
        return shots.stream().mapToInt(i -> i).sum() == 10 || shots.size() == NUMBER_OF_SHOTS;
    }

    public FrameResult getResult() {
        return FrameResult.fromShotResults(shots);
    }
}
