package bowling.frame;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.State;
import bowling.framestate.Ready;
import bowling.framestate.Spare;
import bowling.framestate.Strike;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LastBowlingFrame implements BowlingFrame {

    public static final int LAST_FRAME_MAX_BOWL_COUNT = 3;

    private State state;
    private Pin bonusPin;

    private LastBowlingFrame(final State state, final Pin bonusPin) {
        this.state = state;
        this.bonusPin = bonusPin;
    }

    public static LastBowlingFrame newInstance() {
        return new LastBowlingFrame(Ready.newInstance(), null);
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
            FrameScore updateFrameScore = frameScore.addNextAddingUpScores(getBonusPinToScores());
            return FrameScore.newInstanceWithBonusPin(updateFrameScore, bonusPin, LeftScoreCount.of(0));
        }

        return frameScore.addNextAddingUpScores(Arrays.asList(Score.ofZeroPins(), Score.ofZeroPins()));
    }

    private List<Score> getBonusPinToScores() {
        return Arrays.asList(bonusPin.toScore(), Score.ofZeroPins());
    }

    @Override
    public Score sumScore(final FrameScore beforeScore) {
        FrameScore addingUpFrameScore = state.addNextAddingUpFrameScore(beforeScore);

        if (addingUpFrameScore.canCalculateSelfScore()) {
            return addingUpFrameScore.getScore();
        }

        addingUpFrameScore = beforeScore.addNextAddingUpScores(Arrays.asList(Score.ofZeroPins(), Score.ofZeroPins(), Score.ofZeroPins()));
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
            frameScore = frameScore.addNextAddingUpScores(getBonusPinToScores());
        }

        return frameScore.canCalculateSelfScore();
    }

    @Override
    public boolean canCalculateScore(FrameScore beforeScore) {
        FrameScore addingUpFrameScore = state.addNextAddingUpFrameScore(beforeScore);

        if (!Objects.isNull(bonusPin)) {
            addingUpFrameScore = addingUpFrameScore.addNextAddingUpScores(getBonusPinToScores());
        }

        return addingUpFrameScore.canCalculateSelfScore();
    }

    @Override
    public State getState() {
        return state;
    }

    public Pin getBonusPin() {
        return bonusPin;
    }
}
