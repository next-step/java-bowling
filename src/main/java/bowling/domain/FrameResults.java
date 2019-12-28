package bowling.domain;

import bowling.domain.set.FrameSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameResults {

    private final List<FrameSet> frameSets;

    public FrameResults(List<FrameSet> frameSets) {
        this.frameSets = frameSets;
    }

    public List<FrameSet> getFrameSets() {
        return Collections.unmodifiableList(frameSets);
    }

    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();

        for (FrameSet frameSet : frameSets) {
            addNextScore(scores, frameSet);
        }

        return scores;
    }

    private void addNextScore(List<Integer> scores, FrameSet frameSet) {
        if (!frameSet.canCalculateScore()) {
            return;
        }

        int currentScore = frameSet.getTotalScore();

        if (scores.isEmpty()) {
            scores.add(currentScore);
            return;
        }

        int nextScore = scores.get(scores.size() - 1) + currentScore;
        scores.add(nextScore);
    }
}
