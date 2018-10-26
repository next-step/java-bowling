package bowling.frame;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<FrameResult> getFrameResults() {
        return Collections.unmodifiableList(frameResults);
    }

    @Override
    public String toString() {
        return "Board [frameResults=" + frameResults + "]";
    }
}
