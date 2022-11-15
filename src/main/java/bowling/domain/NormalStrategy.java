package bowling.domain;

public class NormalStrategy implements FrameStrategy{

    public static final int END_ROUND = 2;

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isFrameEnd(int round, Result beforeResult) {
        if (round == END_ROUND && (beforeResult.isStrike() || beforeResult.isSpare())) {
            throw new IllegalArgumentException("유효하지 않은 데이터 입니다.");
        }

        return round == END_ROUND || beforeResult.isStrike();
    }
}
