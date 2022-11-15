package bowling.domain;

public class NormalStrategy implements FrameStrategy {

    public static final int END_ROUND = 2;


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
}
