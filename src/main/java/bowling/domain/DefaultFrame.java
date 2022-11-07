package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultFrame implements Frame {

    private static final int MAX_SCORE = 10;

    private final List<Score> scores = new ArrayList<>();

    public DefaultFrame() {
    }

    public DefaultFrame(int first, int second) {
        validateSum(first, second);
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
    }

    private void validateSum(int first, int second) {
        if ((first + second) > MAX_SCORE) {
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
        DefaultFrame that = (DefaultFrame) o;
        return Objects.equals(this.scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
