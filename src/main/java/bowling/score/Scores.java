package bowling.score;

import java.util.Arrays;
import java.util.List;

public class Scores {

    private Score firstScore;
    private Score secondScore;

    private Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Scores init() {
        return new Scores(null, null);
    }

    public boolean isDone() {
        return firstScore != null && secondScore != null;
    }

    public boolean isStrike() {
        return firstScore != null && firstScore.isStrike();
    }

    public static boolean isSpare(List<Score> scores) {
        return scores.stream()
                .map(Score::getScore)
                .reduce(Integer::sum)
                .map(sum -> sum == Score.MAX_SCORE)
                .orElse(false);
    }

    public List<Score> getResult() {
        return Arrays.asList(firstScore, secondScore);
    }
}
