package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.status.*;

public class NormalFrame implements Frame {

    private int index;
    private Score score;
    private boolean isEnd;
    private NormalFrame nextFrame;
    private FrameStatus status;

    public NormalFrame(int index, int score) {
        this.status = new Ready(false);
        this.index = index;
        bowl(score);
    }

    public static NormalFrame first(int score) {
        return new NormalFrame(0, score);
    }

    public NormalFrame next(int score) {
        if (isEnd) {
            nextFrame = new NormalFrame(index + 1, score);
            return nextFrame;
        }

        bowl(score);
        return this;
    }

    public void bowl(int score) {
        this.status = status.bowl(score);
        this.isEnd = isEndCondition(score);
        this.score = status.findScore();
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public boolean isEndCondition(int score) {
        return this.status instanceof Strike ||
                this.status instanceof Spare ||
                this.status instanceof Miss;
    }

    public int getScore() {
        if (score.canCalucateScore()) {
            return score.getScore();
        }

        if (nextFrame == null) {
            return 0;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    private int calculateAdditionalScore(Score beforeScore) {

        Score score = Score.of(beforeScore).bowl(this.status.getFirstCountOfPin());
        if (score.canCalucateScore()) {
            return score.getScore();
        }

        return calculateAdditionalSecondScore(score);
    }

    private int calculateAdditionalSecondScore(Score beforeScore) {
        if (status instanceof Strike) {
            return getScore();
        }

        if (this.status.getSecondCountOfPin() == 0) {
            return 0;
        }

        Score score = Score.of(beforeScore).bowl(this.status.getSecondCountOfPin());
        if (score.canCalucateScore()) {
            return score.getScore();
        }

        return getScore();
    }

    public FrameStatus getStatus() {
        return status;
    }
}
