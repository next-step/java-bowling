package bowling.step4.domain;

import bowling.step4.domain.scores.Scores;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public enum ScoresType {
    STRIKE(scores -> scores.get(0) == Score.getStrike()),
    SPARED(scores -> !asList(null, Score.getStrike()).contains(scores.get(0))
                     && scores.get(1) != null
                     && scores.get(0).sum(scores.get(1)) == Score.getStrike()),
    EMPTY(scores -> scores.get(0) == null && scores.get(1) == null),
    FULL(scores -> scores.get(0) == Score.getStrike() ||
                   (scores.get(0) != null && scores.get(1) != null));

    private final Function<List<Score>, Boolean> isType;

    ScoresType(Function<List<Score>, Boolean> isType) {
        this.isType = isType;
    }

    public boolean of(Scores scores) {
        return isType.apply(scores.stream().collect(toList()));
    }

    public boolean of(List<Score> scores) {
        return isType.apply(scores);
    }

    public boolean of(Stream<Score> scores) {
        return isType.apply(scores.collect(toList()));
    }
}
