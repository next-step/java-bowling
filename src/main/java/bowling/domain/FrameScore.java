package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface FrameScore {
    int getValue();

    FrameScore plus(FrameScore other);

    static FrameScore unknown = new UnknownScore();

    static FrameScore of(int score){
        return new DefaultScore(score);
    }

    static FrameScore of(List<Integer> scores){
        return new DefaultScore(scores.stream().reduce(Integer::sum).orElse(0));
    }

    static FrameScore of(Integer... scores){
        return new DefaultScore(Arrays.stream(scores).reduce(Integer::sum).orElse(0));
    }

    class DefaultScore implements FrameScore {

        private int score;

        private DefaultScore(int score) {
            this.score = score;
        }

        @Override
        public int getValue() {
            return score;
        }

        @Override
        public FrameScore plus(FrameScore other) {
            score = score + other.getValue();
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DefaultScore that = (DefaultScore) o;
            return score == that.score;
        }

        @Override
        public int hashCode() {
            return Objects.hash(score);
        }
    }

    class UnknownScore implements FrameScore {

        @Override
        public int getValue() {
            return -1;
        }

        @Override
        public FrameScore plus(FrameScore other) {
            return this;
        }

    }
}

