package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Scores {

    private final static int MAX_SCORE_SIZE = 3;

    private final List<Score> scores;

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
        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException("점수는 3개 이상일 수 없어요.");
        }
        this.scores = scores;
    }

    public boolean isClosed() {
        if (scores.size() == 2) {
            return true;
        }

        return sumScore() == 10;
    }

    public boolean isClosedMaxRound() {
        if (scores.size() == 3) {
            return true;
        }

        return scores.size() == 2 && sumScore() < 10;
    }


    public int sumScore() {
        return scores.stream()
            .mapToInt(Score::getScore)
            .sum();
    }


    private static List<Score> toScore(int[] number) {
        return Arrays.stream(number)
            .boxed()
            .map(Score::of)
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scores scores1 = (Scores) o;
        return Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }


}
