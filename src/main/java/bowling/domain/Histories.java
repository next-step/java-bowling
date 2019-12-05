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

    private int getScore(List<Integer> scores, int score) {
        int previousTotalScore = scores.stream()
                .reduce(Integer::sum)
                .orElse(0);

        return previousTotalScore + score;
    }

    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();

        for (FrameSet frameSet : histories) {
            if (frameSet.getTotalScore() == NOT_CALCULATED_SCORE) {
                break;
            }
            scores.add(getScore(scores, frameSet.getTotalScore()));
        }

        return scores;
    }
}
