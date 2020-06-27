package bowling.domain.frame;

public class NormalFrame implements Frame {

    private final int index;

    private NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame createFirst() {
        return new NormalFrame(0);
    }

    @Override
    public Frame createNext(boolean isNextFinal) {
        int nextIndex = index + 1;
        return isNextFinal ? FinalFrame.create(nextIndex) : new NormalFrame(nextIndex);
    }
}
