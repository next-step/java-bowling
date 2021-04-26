package bowling.domain.frame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

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
        if (scores.size() == 3) {
            return true;
        }
        if (scores.size() == 2 && (scores.get(0) != Score.STRIKE && scores.get(1) == Score.SPARE)) {
            return true;
        }
        return false;
    }

    @Override
    public Frame addScore(int score) {
        return FinalFrame.valueOf(3);
    }

    private static List<Score> generateScores(List<Integer> scores) {
        return scores.stream()
                .map(score -> Score.valueOf(score))
                .collect(Collectors.toList());
    }
}
