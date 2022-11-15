package bowling.domain;

public class FinalStrategy implements FrameStrategy {

    public static final int END_ROUND = 3;
    public static final int END_BEFORE_ROUND = 2;

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isFrameEnd(int round, Result beforeResult) {
        if (round == 3 && (beforeResult.isStrike() || beforeResult.isSpare())) {
            throw new IllegalArgumentException("유효하지 않은 입력값 입니다");
        }

        return round == END_ROUND
                || round == END_BEFORE_ROUND && (beforeResult.isMiss() || beforeResult.isGutter());
    }
}
