package bowling.domain;

import bowling.domain.exception.AttemptsExceededException;
import bowling.domain.exception.IncorrectNumberOfPinsException;

import java.util.List;

public class Scores {

    public static final int NUMBER_OF_PINS = 10;
    public static final int FRAME_ATTEMPTS = 2;

    private List<Score> scores;

    public Scores(final List<Score> scores) {
        checkValidAttempts(scores);
        checkValidNumberOfPins(scores);
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
