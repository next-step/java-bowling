package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    public FinalFrame() {
        this.scores = new ArrayList<>();
    }

    public FinalFrame(List<Score> scores) {
        this.scores = scores;
    }

    public static FinalFrame valueOf(int score) {
        return new FinalFrame(Arrays.asList(Score.valueOf(score)));
    }

    public static FinalFrame valueOf(List<Integer> scores) {
        return new FinalFrame(generateScores(scores));
    }

    @Override
    public boolean isFinished() {
        if (scores.isEmpty()) {
            return false;
        }
        if (scores.size() == 3) {
            return true;
        }
        if (scores.size() == 2 && (scores.get(0) != Score.STRIKE && scores.get(1) != Score.SPARE)) {
            return true;
        }
        return false;
    }

    @Override
    public Frame addScore(int score) throws Exception {
        if (isFinished()) {
            throw new Exception();
        }
        if (scores.size() == 0) {
            return new FinalFrame(Arrays.asList(Score.valueOf(score)));
        }
        if (scores.size() == 1) {
            return new FinalFrame(Arrays.asList(scores.get(0), Score.valueOf(scores.get(0), score)));
        }
        if (scores.size() == 2) {
            return new FinalFrame(Arrays.asList(scores.get(1), Score.valueOf(scores.get(1), score)));
        }
        return this;
    }

    private static List<Score> generateScores(List<Integer> scores) {
        return scores.stream()
                .map(score -> Score.valueOf(score))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
