package bowling.domain;

import bowling.domain.set.FrameSet;

import java.util.*;
import java.util.stream.Collectors;

public class FrameResults {

    private final HashSet<FrameSet> results = new HashSet<>();

    public void add(FrameSet frameSet) {
        results.add(frameSet);
    }

    public List<FrameSet> getValue() {
        List<FrameSet> frameSets = results.stream()
                .sorted(Comparator.comparingInt(FrameSet::getPlayCount))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(frameSets);
    }
}
