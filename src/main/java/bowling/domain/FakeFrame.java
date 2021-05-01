package bowling.domain;

public class FakeFrame implements Frame {

    private FakeFrame() {
    }

    public static Frame initialize() {
        return new FakeFrame();
    }
}
