package bowling.domain;

import bowling.domain.set.FrameSet;

import java.util.*;
import java.util.stream.Collectors;

import static bowling.domain.FrameConstants.NOT_CALCULATED_SCORE;

public class Histories {

    private final HashSet<FrameSet> histories = new HashSet<>();

    public void add(FrameSet history) {
        histories.add(history);
    }

    public List<FrameSet> getValue() {
        List<FrameSet> values = histories.stream()
                .sorted(Comparator.comparingInt(FrameSet::getPlayCount))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(values);
    }

    private void addNextScore(List<Integer> scores, int score) {
        if (scores.isEmpty()) {
            scores.add(score);
            return;
        }

        int nextScore = scores.get(scores.size() - 1) + score;
        scores.add(nextScore);
    }

    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();

        for (FrameSet frameSet : histories) {
            if (!frameSet.canCalculateScore()) {
                break;
            }
            addNextScore(scores, frameSet.getTotalScore());
        }

        return scores;
    }
}
