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
        if (!isDone()) {
            return Optional.empty();
        }

        int score = 0;

        if (getFirstScoreAsOptional().isPresent()) {
            score += firstScore();
        }

        if (getSecondScoreAsOptional().isPresent()) {
            score += secondScore();
        }

        if (extraScore != null) {
            score += extraScore.get();
        }

        return Optional.ofNullable(score);
    }

    @Override
    public void shot(int hitCount) {
        if (isPlayedTwice() && checkExtraShot()) {
            extraScore = new Score(hitCount);
            return;
        }

        super.shot(hitCount);

        if (checkExtraShot()) {
            pins = new Pins();
        }
    }

    private boolean isPlayedTwice() {
        return scores.isPlayTwice();
    }

    private boolean checkExtraShot() {
        return scores.isSpare() || scores.isStrike();
    }

    @Override
    public boolean isDone() {
        return (!checkExtraShot() && isPlayedTwice())
            || Objects.nonNull(extraScore);
    }

    public Optional<Score> getExtraScore() {
        return Optional.ofNullable(extraScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(extraScore, that.extraScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extraScore);
    }
}
