package bowling.domain.scores;

import bowling.Score;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Scores {

    protected final List<Score> scores;

    public Scores() {
        this(new ArrayList<>());
    }

    public Scores(int... number) {
        this(toScore(number));
    }

    public Scores(Score... scores) {
        this(Arrays.asList(scores));
    }

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    public abstract boolean isClosed();

    public int sumScore() {
        return scores.stream()
            .mapToInt(Score::getScore)
            .sum();
    }

    protected static List<Score> toScore(int[] number) {
        return Arrays.stream(number)
            .boxed()
            .map(Score::of)
            .collect(Collectors.toList());
    }
}
