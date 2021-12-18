package bowling;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {

    private final static int MAX_SCORE_SIZE = 3;

    private final List<Score> scores;

    public Scores(int... number) {
        this(toScore(number));
    }

    public Scores(Score... scores) {
        this(Arrays.asList(scores));
    }

    public Scores(List<Score> scores) {
        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException("점수는 3개 이상일 수 없어요.");
        }
        this.scores = scores;
    }


    private static List<Score> toScore(int[] number) {
        return Arrays.stream(number)
            .boxed()
            .map(Score::of)
            .collect(Collectors.toList());
    }
}
