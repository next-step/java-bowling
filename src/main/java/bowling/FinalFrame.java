package bowling;

public class FinalFrame extends Frame {

    public FinalFrame() {
    }

    public FinalFrame(int index) {
        this.index = index;
    }

    @Override
    Frame next() {
        if (this.index > 11) {
            throw new IllegalArgumentException("경기가 종료 되었습니다.");
        }

        if (this.isStrike() || this.isSpare()) {
            return new FinalFrame();
        }

        return null;
    }
}
