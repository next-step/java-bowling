package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class LastFrame implements Frame {

    private static final int STRIKE = 10;
    private static final int MAX_SCORE = 10;

    private final List<Score> scores;

    public LastFrame(int first, int second, int third) {
        validateSum(first, second);
        this.scores = new ArrayList<>();
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
        this.scores.add(Score.of(third));
    }

    private void validateSum(int first, int second) {
        if (first != STRIKE && (first + second) > MAX_SCORE) {
            throw new IllegalArgumentException();
        }
    }
}
