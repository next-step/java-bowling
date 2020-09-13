package bowling.domain;

public class NormalFrame extends Frame {

    protected NormalFrame(int number) {
        super(number);
    }

    public static NormalFrame from() {
        return new NormalFrame(1);
    }
}
