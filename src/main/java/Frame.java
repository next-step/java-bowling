import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frame {
    public List<Integer> scores;

    public Frame() {
        this.scores = new ArrayList<>();
    }

    public static Frame of(int score) {
        Frame frame = new Frame();
        frame.addScore(score);
        return frame;
    }

    public void addScore(int score) {
        if (sumScore() + score > 10) {
            throw new IllegalArgumentException("점수들의 합은 10을 넘길 수 없습니다.");
        }
        this.scores.add(score);
    }

    public int sumScore() {
        return scores.stream().reduce(Integer::sum).orElse(0);
    }

    public Score getScore() {
        int score = sumScore();
        return Score.get(scores.size(), score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}
