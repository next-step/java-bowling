package bowling.domain;

import java.util.Objects;
import java.util.Optional;

public class FinalFrame extends Frame {
    private Score extraScore;

    public FinalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public Optional<Integer> scoreCalculated() {
        Integer score = 0;

        if (getFirstScoreAsOptional().isPresent()) {
            score += firstScore();
        }

        if (getSecondScoreAsOptional().isPresent()) {
            score += secondScore();
        }

        if (extraScore != null) {
            score += extraScore.get();
        }

        return Optional.of(score);
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
