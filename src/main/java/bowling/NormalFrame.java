package bowling;

public class NormalFrame implements Frame {
    public static int MIN_FRAMES = 1;
    public static int MAX_FRAMES = 9;

    private int frameNumber;
    private NormalThrown normalThrown;

    private NormalFrame(int frameNumber, NormalThrown normalThrown) {
        valid(frameNumber);
        this.frameNumber = frameNumber;
        this.normalThrown = normalThrown;
    }

    public static NormalFrame of(int frameNumber, int countOfPins) {
        Pins pins = Pins.from(countOfPins);
        return new NormalFrame(frameNumber, new NormalThrown(pins));
    }

    @Override
    public int getScore() {
        return normalThrown.getScore();
    }

    private void valid(int frameNumber) {
        if (frameNumber < MIN_FRAMES || frameNumber > MAX_FRAMES) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public Frame bowl(int countOfPins) {
        normalThrown.bowl(countOfPins);
        return this;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public int firstPins() {
        return normalThrown.firstPins();
    }

    @Override
    public int secondPins() {
        return normalThrown.secondPins();
    }

    @Override
    public boolean isFinished() {
        return normalThrown.isFinished();
    }
}
