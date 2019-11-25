package game.bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yusik on 2019/11/25.
 */
public class Score {

    private Integer firstScore;
    private Integer secondScore;

    public Score(Integer firstScore, Integer secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Score empty() {
        return new Score(null, null);
    }

    public Integer getFirstScore() {
        return firstScore;
    }

    public Integer getSecondScore() {
        return secondScore;
    }

    public List<Integer> getScores() {
        return Stream.of(firstScore, secondScore)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return getScores().size() == 0;
    }

    public boolean isThrowing() {
        return getScores().size() == 1;
    }

    public boolean isFinal() {
        return getScores().size() == 3;
    }

    @Override
    public String toString() {
        return "Score{" +
                "firstScore=" + firstScore +
                ", secondScore=" + secondScore +
                '}';
    }
}
