package bowling.domain.scores;

import bowling.Score;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GeneralScores extends Scores {

    private static final int MAX_SCORE_SIZE = 2;
    private static final int MAX_OF_SCORE = 10;


    public GeneralScores() {
        this(new ArrayList<>());
    }

    public GeneralScores(int... numbers) {
        this(toScore(numbers));
    }

    public GeneralScores(Score... scores) {
        this(Arrays.asList(scores));
    }

    public GeneralScores(List<Score> scores) {
        super(scores);

        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_SCORE_SIZE));
        }

        if (sumScore() > MAX_OF_SCORE) {
            throw new IllegalArgumentException(String.format("라운드는 %d점을 넘길 수 없어요.", MAX_OF_SCORE));
        }
    }

    @Override
    public Scores add(int hitCount) {
        if (scores.size() >= MAX_SCORE_SIZE) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_SCORE_SIZE));
        }

        List<Score> tempScores = new ArrayList<>(this.scores);
        tempScores.add(Score.of(hitCount));
        return new FinalScores(tempScores);
    }

    @Override
    public boolean isClosed() {
        if (scores.size() == MAX_SCORE_SIZE) {
            return true;
        }

        return containStrike();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GeneralScores scores1 = (GeneralScores) o;
        return Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

}
