package bowling.domain;

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
    public Score getScore(RollingResult rollingResult) {
        Result result = rollingResult.getResult();
        Score current = Score.of(rollingResult.getPinCount());

        if (result.isStrike()) {
            return current
                    .add(rollingResult.getAfterPinCount())
                    .add(rollingResult.getAfterAfterPinCount());
        }

        if (result.isSpare()) {
            return current.add(rollingResult.getAfterPinCount());
        }

        return current;
    }
}
