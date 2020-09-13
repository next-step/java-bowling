package bowling.domain;

public class NormalFrame extends Frame {

    protected NormalFrame(int number) {
        super(number);
    }

    public static NormalFrame from() {
        return new NormalFrame(1);
    }

    public static NormalFrame of(int number) {
        return new NormalFrame(number);
    }
}
