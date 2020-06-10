package bowling.step2.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class FrameScore {
    private final List<Score> scores;

    private FrameScore (List<Score> scores) {
        this.scores = scores;
    }

    public static FrameScore of (List<Score> scores) {
        return new FrameScore(scores);
    }

    public static FrameScore ofStrike () {
        return Stream.of(Score.getStrike())
                     .collect(collectingAndThen(toList(), FrameScore::of));
    }

    public boolean isStriked () {
        Score strike = Score.getStrike();
        return scores.size() == 1 &&
               scores.contains(strike);
    }

    public boolean isSpared () {
        if (scores.size() != 2) {
            return false;
        }
        return Score.sum(scores) == Score.getStrike();
    }

    public Stream<Score> stream () {
        return scores.stream();
    }
}