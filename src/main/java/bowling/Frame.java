package bowling;

import java.util.Objects;

public class Frame {
    private int frameNo;
    private Scores scores;

    Frame() {
        this(1);
    }

    public Frame(int frameNo) {
        this.frameNo = frameNo;
        this.scores = new Scores();
    }

    public Frame next(int number) {
        scores.add(number, frameNo);
        if (scores.done(frameNo)) {
            return new Frame(this.frameNo + 1);
        }
        return this;
    }

    public int getFrameNo() {
        return frameNo;
    }

    public Score getScore(int index) {
        return scores.getScores().get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNo == frame.frameNo &&
                Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, scores);
    }
}
