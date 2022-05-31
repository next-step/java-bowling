package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

public class FinalFrame extends Frame {
    private Score firstScore;
    private Score secondScore;
    private Score extraScore;

    public FinalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public OptionalInt scoreCalculated() {
        if (!isDone()) {
            return OptionalInt.empty();
        }

        int score = firstScore.get() + secondScore.get();

        if (extraScore != null) {
            score += extraScore.get();
        }

        return OptionalInt.of(score);
    }

    @Override
    public void shot(int hitCount) {
        if (isDone()) {
            checkValidShot(hitCount);
            return;
        }

        super.shot(hitCount);

        if (firstScore == null) {
//            firstScore = new Score(hitCount);
        }
    }

    private void checkValidShot(int hitCount) {
        if (!isSpareOrStrike()) {
            throw new IllegalStateException("마지막 프레임에서 보너스투구 기회를 얻지 못하고 세번의 투구는 불가.");
        }

//        extraScore = new Score(hitCount);
    }

    private boolean isSpareOrStrike() {
        return state() instanceof Strike
            || state() instanceof Spare;
    }

    private boolean isPlayedTwice() {
        return firstScore != null && secondScore != null;
    }

    @Override
    public boolean isDone() {
        return (!isSpareOrStrike() && isPlayedTwice())
            || Objects.nonNull(extraScore);
    }

    public Optional<Score> getExtraScore() {
        return Optional.ofNullable(extraScore);
    }

}
