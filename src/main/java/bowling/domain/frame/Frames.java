package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Frames {
    private final List<Scores> scores;
    public static final Integer FINAL_FRAME = 9;

    public Frames() {
        this(generateFrames());
    }

    public Frames(List<Scores> scores) {
        this.scores = scores;
    }

    public Frames addScore(int score) throws Exception {
        if (isFinished()) {
            throw new IndexOutOfBoundsException();
        }
        this.scores.get(nowFrame()).addScore(score);
        return new Frames(this.scores);
    }

    public boolean isFinished() {
        if (nowFrame() > FINAL_FRAME) {
            return true;
        }
        return false;
    }

    public int nowFrame() {
        return IntStream.range(0, 10)
                .filter(i -> !scores.get(i).isFinished())
                .findFirst()
                .orElse(10);
    }

    public Scores getFrame(int index) {
        return this.scores.get(index);
    }

    private static List<Scores> generateFrames() {
        List<Scores> result = new LinkedList<>();
        IntStream.range(0, FINAL_FRAME)
                .forEach(i -> result.add(new NormalScores()));
        result.add(new FinalScores());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames frames1 = (Frames) o;
        return Objects.equals(scores, frames1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + scores +
                '}';
    }
}
