package bowling.entity;

import bowling.entity.frame.FrameResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingBoard {
    private final List<FrameResult> frameResults;

    public BowlingBoard() {
        this.frameResults = new ArrayList<>();
    }

    public void addResult(FrameResult frameResult){
        frameResults.add(frameResult);
    }

    public List<FrameResult> boardResult() {
        return Collections.unmodifiableList(frameResults);
    }
}
