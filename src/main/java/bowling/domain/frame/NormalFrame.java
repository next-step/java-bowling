package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame extends Frame {

    private static final int MAX_SUM = 10;

    private final NormalFrameNo frameNo;

    public NormalFrame(int frameNo, int firstNo, int secondNo) {
        super(firstNo, secondNo);
        validateNumbers(firstNo, secondNo);
        this.frameNo = NormalFrameNo.of(frameNo);
    }

    private void validateNumbers(int firstNo, int secondNo) {
        if (firstNo + secondNo > MAX_SUM) {
            throw new IllegalArgumentException("invalid normal frame numbers: " + firstNo + ", " + secondNo);
        }
    }

    @Override
    public Frame next(int firstNo, int secondNo) {
        if (frameNo.isMax()) {
            return new FinalFrame(firstNo, secondNo);
        }
        return new NormalFrame(frameNo.next(), firstNo, secondNo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNo, that.frameNo) && Objects.equals(pinNumbers, that.pinNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, pinNumbers);
    }
}
