package bowling.model.frame;

import bowling.model.Pins;

import java.util.Arrays;
import java.util.List;

import static bowling.model.Pins.DOWN_ALL;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Scores {

    private List<Pins> scores;

    private Scores(List<Pins> scores) {
        this.scores = scores;
    }

    static Scores of(Pins... downPins) {
        return Arrays.stream(downPins)
                .collect(collectingAndThen(toList(), Scores::new));
    }

    boolean isFinished() {
        Pins totalScore = scores.stream()
                .reduce(Pins.DOWN_ZERO, Pins::sum);
        return totalScore.equals(DOWN_ALL) || scores.size() == 2;
    }
}
