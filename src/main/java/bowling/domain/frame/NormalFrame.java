package bowling.domain.frame;

import bowling.domain.Shots;
import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.Shot;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int SHOT_LIMIT = 2;

    private final Shots shots;
    private Frame next;
    private final int frameNumber;

    private NormalFrame(Shots shots, int frameNumber) {
        this.shots = shots;
        this.frameNumber = frameNumber;
    }

    public static NormalFrame init() {
        return new NormalFrame(Shots.of(), 1);
    }

    @Override
    public Frame next() {
        if (frameNumber < FinalFrame.FRAME_NUMBER - 1) {
            next = new NormalFrame(Shots.of(), this.frameNumber + 1);
            return next;
        }
        next = FinalFrame.of();
        return next;
    }

    @Override
    public void shot(int shot) {
        if (isFrameSet()) {
            throw new IllegalStateException(String.format("shot Frame fail. this FinalFrame already calculated instance=%s", this));
        }

        addShot(shot);
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    private void addShot(int shot) {
        shots.add(shot);
    }

    @Override
    public boolean isFrameSet() {
        return shots.hasSize(SHOT_LIMIT) ||
                shots.hasClear();
    }

    @Override
    public FrameScore getFrameScore() {
        if (!isFrameSet()) {
            return DefaultFrameScore.NULL;
        }
        return getFrameScoreWithAddBonus();
    }

    private FrameScore getFrameScoreWithAddBonus() {
        FrameScore frameScore = DefaultFrameScore.of(shots.totalScore(), shots.getLastType().getBonusCount());
        return Objects.isNull(next) ? frameScore : next.addBonus(frameScore);
    }

    @Override
    public FrameScore addBonus(FrameScore beforeScore) {
        for (Shot shot : shots.shots()) {
            addBonus(beforeScore, shot);
        }
        if (beforeScore.isCalculated() || Objects.isNull(next)) {
            return beforeScore;
        }
        return next.addBonus(beforeScore);
    }

    private void addBonus(FrameScore beforeScore, Shot shot) {
        if (!beforeScore.isCalculated()) {
            beforeScore.addBonus(shot.singleScore());
        }
    }

    @Override
    public List<Shot> shots() {
        return shots.shots();
    }

    @Override
    public int getShotsCount() {
        return shots.shots().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "shots=" + shots +
                '}';
    }
}
