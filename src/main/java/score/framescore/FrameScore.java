package score.framescore;

import java.util.Objects;

public class FrameScore {

    private int score;
    private int addCount;

    public FrameScore(int score, int addCount) {
        this.score = score;
        this.addCount = addCount;
    }

    public int getScore() {
        return score;
    }

    public boolean canAdd() {
        return addCount > 0;
    }

    public int getAddCount() {
        return addCount;
    }

    public FrameScore addScore(int score) {
        return new FrameScore(this.score + score, addCount - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return score == that.score &&
                addCount == that.addCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, addCount);
    }
}
