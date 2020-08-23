package bowling.domian.frame;

import bowling.domian.frame.exception.FrameEndException;
import bowling.domian.frame.exception.InvalidScoreCalculateException;
import bowling.domian.state.running.Ready;
import bowling.domian.state.finished.Spare;
import bowling.domian.state.State;
import bowling.domian.state.finished.Strike;

import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private State normalState;
    private State bonusState;

    public FinalFrame() {
        this.normalState = new Ready();
        this.bonusState = new Ready();
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame bowl(int falledPinsCount) {
        if (!normalState.isFinished()) {
            normalState = normalState.bowl(falledPinsCount);
            return this;
        }

        if (isNormalStrikeOrSpare() && isBonusReady()) {
            bonusState = bonusState.bowl(falledPinsCount);
            return this;
        }

        throw new FrameEndException();
    }

    @Override
    public Score calculateAdditional(Score score) {
        if (this.normalState instanceof Ready) {
            return score;
        }

        score = this.normalState.calculateAdditional(score);
        if (score.isCalculateDone() || bonusState instanceof Ready) {
            return score;
        }

        return this.bonusState.calculateAdditional(score);
    }

    public FrameResult getFrameResult() {
        if (!this.normalState.canGetScore()) {
            return FrameResult.of(this.normalState.getDesc());
        }

        return FrameResult.of(getNormalScore(),
                getBonusScore(),
                getDesc());
    }

    private Score getNormalScore() {
        return this.normalState.getScore();
    }

    private Optional<Score> getBonusScore() {
        try {
            return Optional.of(this.bonusState.getScore());
        } catch (InvalidScoreCalculateException e) {
            return Optional.empty();
        }

    }

    private String getDesc() {
        String bonusDesc = bonusState.getDesc();

        if (bonusDesc.length() < 1) {
            return normalState.getDesc();
        }

        return normalState.getDesc() + "|" + bonusDesc;
    }

    @Override
    public boolean isGameEnd() {
        if (!normalState.isFinished()) {
            return false;
        }

        return !isNormalStrikeOrSpare() || !isBonusReady();
    }

    private boolean isNormalStrikeOrSpare() {
        return normalState instanceof Spare ||
                normalState instanceof Strike;
    }

    private boolean isBonusReady() {
        return bonusState instanceof Ready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(normalState, that.normalState) &&
                Objects.equals(bonusState, that.bonusState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalState, bonusState);
    }
}
