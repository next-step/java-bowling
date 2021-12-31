package bowling.domain;

import java.util.Objects;

public class NormalFrame extends Frame {
    private final FrameIndex index;
    private final Balls balls;
    private Frame next;

    private NormalFrame(FrameIndex index, Balls balls) {
        this.index = index;
        this.balls = balls;
    }

    public static NormalFrame of(FrameIndex index, Balls balls) {
        return new NormalFrame(index, balls);
    }

    public static NormalFrame next(FrameIndex index) {
        return of(index, Balls.init());
    }

    public static NormalFrame init() {
        return next(FrameIndex.first());
    }

    @Override
    public Frame bowl(Ball ball) {
        balls.bowl(ball);
        if (isEnd()) {
            return next();
        }
        return this;
    }

    private Frame next() {
        if (index.next().max()) {
            this.next = FinalFrame.init();
            return this.next;
        }
        this.next = next(index.next());
        return this.next;
    }

    @Override
    public boolean isEnd() {
        return balls.isEnd();
    }

    public int getFrameIndex() {
        return index.getIndex();
    }

    @Override
    public String symbol() {
        return balls.symbol();
    }

    @Override
    public Score score() {
        if (!balls.isEnd()) {
            return Score.init();
        }
        Score score = balls.score();
        if (score.canCalculate()) {
            return score;
        }
        return next.additionalScore(score);
    }

    @Override
    public Score additionalScore(Score previous) {
        Score nextScore = balls.score(previous);
        if (nextScore.canCalculate()) {
            return nextScore;
        }
        if (next == null) {
            return nextScore.next();
        }
        return next.additionalScore(nextScore);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        NormalFrame frame = (NormalFrame) obj;

        return Objects.equals(index, frame.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
