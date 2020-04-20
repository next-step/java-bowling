package bowling.frame;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.dto.FrameState;
import bowling.framestate.Ready;
import bowling.framestate.Spare;
import bowling.framestate.State;
import bowling.framestate.Strike;

import java.util.Collections;
import java.util.Objects;

public class LastBowlingFrame implements BowlingFrame {

    private State state;
    private Pin bonusPin;

    private LastBowlingFrame(final State state) {
        this.state = state;
        this.bonusPin = null;
    }

    public static LastBowlingFrame newInstance() {
        return new LastBowlingFrame(Ready.newInstance());
    }

    @Override
    public void bowl(final Pin pinCount) {
        if (canBowlBonusPin()) {
            bonusPin = pinCount;
            return;
        }

        state = state.bowl(pinCount);
    }

    private boolean canBowlBonusPin() {
        return (state instanceof Spare || state instanceof Strike) && Objects.isNull(bonusPin);
    }

    @Override
    public Score getTotalScore(final Score beforeScore) {
        return getFrameScore().add(beforeScore);
    }

    @Override
    public Score getFrameScore() {
        FrameScore frameScore = state.createFrameScore();

        if (frameScore.canCalculateSelfScore()) {
            return frameScore.getScore();
        }

        return makeAddingBonusPinFrameScore().getScore();
    }

    private FrameScore makeAddingBonusPinFrameScore() {
        FrameScore frameScore = state.createFrameScore();

        if (!Objects.isNull(bonusPin)) {
            FrameScore updateFrameScore = frameScore.makeFrameScoreWithSumScore(Collections.singletonList(bonusPin.toScore()));
            return FrameScore.newInstanceWithBonusPin(updateFrameScore, bonusPin);
        }

        return FrameScore.newInstance(frameScore, LeftScoreCount.of(0));
    }

    @Override
    public Score sumBeforeScore(final FrameScore beforeScore) {
        FrameScore addingUpFrameScore = state.sumBeforeScore(beforeScore);

        if (addingUpFrameScore.canCalculateSelfScore()) {
            return addingUpFrameScore.getScore();
        }

        addingUpFrameScore = FrameScore.newInstance(beforeScore, LeftScoreCount.of(0));
        return addingUpFrameScore.getScore();
    }

    @Override
    public BowlingFrame appendNextFrame(final int frameNumber) {
        throw new IllegalStateException("It is last frame. can not add next frame");
    }

    @Override
    public boolean isOver() {
        if (canBowlBonusPin()) {
            return false;
        }

        return state.isOver();
    }

    @Override
    public boolean canCalculateScore() {
        FrameScore frameScore = state.createFrameScore();

        if (!Objects.isNull(bonusPin)) {
            frameScore = FrameScore.newInstanceWithBonusPin(frameScore, bonusPin);
        }

        return frameScore.canCalculateSelfScore();
    }

    @Override
    public boolean canCalculateWithBeforeScore(FrameScore beforeScore) {
        FrameScore addingUpFrameScore = state.sumBeforeScore(beforeScore);

        if (!Objects.isNull(bonusPin)) {
            addingUpFrameScore = FrameScore.newInstanceWithBonusPin(addingUpFrameScore, bonusPin);
        }

        return addingUpFrameScore.canCalculateSelfScore();
    }

    @Override
    public FrameState makeFrameState() {
        return FrameState.newInstance(state, bonusPin);
    }
}
