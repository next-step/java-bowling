package game.bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yusik on 2019/11/26.
 */
public class FinalScore extends Score {

    private Integer thirdScore;

    public FinalScore(Integer firstScore, Integer secondScore, Integer thirdScore) {
        super(firstScore, secondScore);
        this.thirdScore = thirdScore;
    }

    public static FinalScore empty() {
        return new FinalScore(null, null, null);
    }

    public List<Integer> getScores() {
        return Stream.of(getFirstScore(), getSecondScore(), thirdScore)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Integer getThirdScore() {
        return thirdScore;
    }

    @Override
    public String toString() {
        return "FinalScore{" +
                "thirdScore=" + thirdScore +
                '}';
    }
}
