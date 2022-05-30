package bowling.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

public class FinalFrame extends Frame {
    private Score extraScore;

    public FinalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public OptionalInt scoreCalculated() {
        if (!isDone()) {
            return OptionalInt.empty();
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

        return OptionalInt.of(score);
    }

    @Override
    public void shot(int hitCount) {
        if (isPlayedTwice()) {
            checkValidShot(hitCount);
            return;
        }

        super.shot(hitCount);

        if (isSpareOrStrike()) {
            pins = new Pins();
        }
    }

    private void checkValidShot(int hitCount) {
        if (!isSpareOrStrike()) {
            throw new IllegalStateException("마지막 프레임에서 보너스투구 기회를 얻지 못하고 세번의 투구는 불가.");
        }

        extraScore = new Score(hitCount);
    }

    private boolean isPlayedTwice() {
        return scores.isPlayTwice();
    }

    private boolean isSpareOrStrike() {
        return scores.isSpare() || scores.isStrike();
    }

    @Override
    public boolean isDone() {
        return (!isSpareOrStrike() && isPlayedTwice())
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
