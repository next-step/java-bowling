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
        return false;
    }

    @Override
    public void addScore(int score) {

    }

    private static List<Score> generateScores(List<Integer> scores) {
        return scores.stream()
                .map(score -> Score.valueOf(score))
                .collect(Collectors.toList());
    }
}
