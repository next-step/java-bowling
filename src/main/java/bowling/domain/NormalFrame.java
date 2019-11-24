package bowling.domain;

public class NormalFrame extends Frame {

    public static NormalFrame first(int score) {
        return new NormalFrame(score);
    }

    public NormalFrame next(int score) {
        if (isEnd) {
            return first(score);
        }

        addScore(score);
        return this;
    }

    public NormalFrame(int score) {
        addScore(score);
    }

    @Override
    public boolean isEndCondition(int score) {
        return this.scores.size() > 1 || score == FRAME_MAX_SCORE;
    }

}
