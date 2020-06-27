package bowling.domain.frame;

public class FinalFrame implements Frame {

    private final int index;

    private FinalFrame(int index) {
        this.index = index;
    }

    public static Frame create(int index) {
        return new FinalFrame(index);
    }

    @Override
    public Frame createNext(boolean isNextLast) {
        throw new UnsupportedOperationException("마지막 프레임의 다음 프레임은 없습니다");
    }
}
