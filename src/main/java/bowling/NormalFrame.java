package bowling;

public class NormalFrame extends Frame{

    public NormalFrame() {
    }

    public NormalFrame(int index) {
        this.index = index;
    }

    @Override
    public Frame next() {
        if (this.index < 9) {
            return new NormalFrame(this.index + 1);
        }
        return new FinalFrame(this.index + 1);
    }
}
