package bowling.domain;

import bowling.domain.exception.AttemptsExceededException;
import bowling.domain.exception.IncorrectNumberOfPinsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scores {

    public static final int NUMBER_OF_PINS = 10;
    public static final int FRAME_ATTEMPTS = 2;

    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public Scores(final List<Score> scores) {
        checkConstructor(scores);
        this.scores = scores;
    }

    private void checkConstructor(final List<Score> scores) {
        checkValidAttempts(scores);
        checkValidNumberOfPins(scores);
    }

    public void roll(final Score score) {
        List<Score> scores = Stream.of(this.scores, Collections.singletonList(score))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        checkConstructor(scores);
        this.scores = scores;
    }

    private void checkValidNumberOfPins(final List<Score> scores) {
        int pins = scores.stream()
                .map(Score::getNumberOfPins)
                .reduce(0, Integer::sum);

        if (pins > NUMBER_OF_PINS) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    private void checkValidAttempts(final List<Score> scores) {
        if (scores.size() > FRAME_ATTEMPTS) {
            throw new AttemptsExceededException();
        }
    }
}
