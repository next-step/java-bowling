package bowling.domain;

import bowling.domain.state.PinCount;

public interface Frame {

    static Frame fakeFrame() {
        return FakeFrame.initialize();
    }

    Frame bowl(PinCount hitCount);

    boolean isFinish();

    int index();

    int size();

    int firstCount();

    int secondCount();

    int thirdCount();

}
