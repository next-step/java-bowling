package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private List<FrameResult> frameResults;
    private int totalScore = 0;

    public Board() {
        frameResults = new ArrayList<>();
    }

    public void add(FrameResult frameResult) {
        if (!frameResult.isUnScore()) {
            totalScore = frameResult.addTotalScore(totalScore);
        }
        frameResults.add(frameResult);
    }

    public List<FrameResult> getFrameResults() {
        return Collections.unmodifiableList(new ArrayList<>(frameResults));
    }
}
