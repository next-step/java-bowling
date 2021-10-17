package bowling.model.frame;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<FrameResult> frameResults;
    private int totalScore = 0;

    public Board() {
        frameResults = new ArrayList<>();
    }

    void add(FrameResult result) {
        if (!result.isUnScore()) {
            totalScore = result.addTotalScore(totalScore);
        }
        frameResults.add(result);
    }

    @Override
    public String toString() {
        return "Board [frameResults=" + frameResults + "]";
    }
}
