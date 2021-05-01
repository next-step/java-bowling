package bowling.domain;

public interface Frame {

    static Frame initialize() {
        return FakeFrame.initialize();
    }
}
