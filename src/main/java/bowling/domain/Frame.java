package bowling.domain;

public interface Frame {

    static Frame fakeFrame() {
        return FakeFrame.initialize();
    }

    Frame bowl(HitCount hitCount);

    boolean isFinish();

    int index();

    int size();

    int firstCount();

    int secondCount();

    int thirdCount();

}
