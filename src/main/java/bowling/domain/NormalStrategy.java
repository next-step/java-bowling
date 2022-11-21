package bowling.domain;

import java.util.Optional;

public class NormalStrategy implements FrameStrategy {

    private static final int END_ROUND = 2;

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isFrameEnd(int round, Result beforeResult) {
        if (beforeResult.isNone()) {
            return false;
        }

        return round == END_ROUND || beforeResult.isStrike();
    }

    @Override
    public Optional<Score> getScore(RollingResult rollingResult) {
        Result result = rollingResult.getResult();
        Score current = Score.of(rollingResult.getPinCount());

        if (result.isStrike()) {
            return getStrikeScore(rollingResult, current);
        }

        if (result.isSpare()) {
            return getSpareScore(rollingResult, current);
        }

        return Optional.of(current);
    }

    private static Optional<Score> getSpareScore(RollingResult rollingResult, Score current) {
        Optional<PinCount> afterPinCount = rollingResult.getAfterPinCount();

        if (afterPinCount.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(current.add(afterPinCount.get()));
    }

    private Optional<Score> getStrikeScore(RollingResult rollingResult, Score current) {
        Optional<PinCount> afterPinCount = rollingResult.getAfterPinCount();
        Optional<PinCount> afterAfterPinCount = rollingResult.getAfterAfterPinCount();

        if (afterPinCount.isEmpty()) {
            return Optional.empty();
        }

        if (afterAfterPinCount.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(current
                .add(afterPinCount.get())
                .add(afterAfterPinCount.get()));
    }
}
