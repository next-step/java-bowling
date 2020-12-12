package bowling.domain.frame;

import bowling.domain.score.FinalScores;
import bowling.domain.score.NormalScores;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static NormalFrame of(int frameNumber, Scores scores) {
        return new NormalFrame(frameNumber, scores);
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.FIRST_FRAME, NormalScores.init());
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;
        Frame nextFrame = nextFrameNumber < Frame.LAST_FRAME
                ? NormalFrame.of(nextFrameNumber, NormalScores.init())
                : FinalFrame.of(nextFrameNumber, FinalScores.init());

        addNext(nextFrame);
        return nextFrame;
    }

    private void addNext(Frame nextFrame) {
        this.next = nextFrame;
    }

    @Override
    protected Score getSpareTotalScore() {
        if (!next.hasFirstScore()) {
            return Score.INVALID_SCORE;
        }

        return scores.getTotalScore()
                .sum(next
                        .getScore(SPARE_BONUS_COUNT));
    }

    @Override
    protected Score getStrikeTotalScore() {
        if (next.canBowl()) {
            return Score.INVALID_SCORE;
        }

        if (next.scores.isStrike()) {
            return next.getTwoStrikeTotalScore(scores.getTotalScore());
        }

        return scores.getTotalScore()
                .sum(next.scores
                        .getTotalScore());
    }

    @Override
    protected Score getTwoStrikeTotalScore(Score lastTotalScore) {
        if (next.canBowl()) {
            return Score.INVALID_SCORE;
        }

        return lastTotalScore
                .sum(scores.getTotalScore())
                .sum(next.scores.getTotalScore());
    }
}
