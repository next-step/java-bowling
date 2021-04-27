package bowling.domain.frame;


import java.util.List;
import java.util.Objects;

public abstract class Frame {
    protected List<Score> scores;

    public abstract boolean isFinished();

    public abstract Frame addScore(int score) throws Exception;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + scores +
                '}';
    }
}
