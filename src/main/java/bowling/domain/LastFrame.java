package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastFrame lastFrame = (LastFrame) o;
        return Objects.equals(this.scores, lastFrame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
