package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<FrameResult> frameResults;
    private int totalScore = 0;

    public Board() {
        frameResults = new ArrayList<>();
    }

    public void add(FrameResult frameResult) {
        frameResults.add(frameResult);
    }
}
