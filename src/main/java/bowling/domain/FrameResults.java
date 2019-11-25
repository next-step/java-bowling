package bowling.domain;

import bowling.domain.set.FrameSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameResults {

    private final List<FrameSet> results = new ArrayList<>();

    public void add(FrameSet frameSet) {
        results.add(frameSet);
    }

    public List<FrameSet> getValue() {
        return Collections.unmodifiableList(results);
    }
}
