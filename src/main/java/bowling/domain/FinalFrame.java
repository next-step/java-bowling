package bowling.domain;

import java.util.Objects;

public class FinalFrame extends Frame {
    private Score extraScore;

    public FinalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public void shot(int hitCount) {
        if (isEndOfNormalTry() && checkExtraShot()) {
            extraScore = new Score(hitCount);
            return;
        }

        super.shot(hitCount);

        if (checkExtraShot()) {
            pins = new Pins();
        }
    }

    private boolean isEndOfNormalTry() {
        return scores.isPlayTwice();
    }

    private boolean checkExtraShot() {
        return scores.isSpare() || scores.isStrike();
    }

    @Override
    public boolean isDone() {
        return (!checkExtraShot() && isEndOfNormalTry())
            || Objects.nonNull(extraScore);
    }

    public Score getExtraScore() {
        return extraScore;
    }
}
