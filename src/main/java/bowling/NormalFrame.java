package bowling;

public class NormalFrame implements Frame {
    public static int MIN_FRAMES = 1;
    public static int MAX_FRAMES = 9;

    private int frameNumber;
    private NormalThrown norMalThrown;

    private NormalFrame(int frameNumber, NormalThrown norMalThrown) {
        valid(frameNumber);
        this.frameNumber = frameNumber;
        this.norMalThrown = norMalThrown;
    }

    public static NormalFrame of(int frameNumber, int countOfPins) {
        Pins pins = Pins.from(countOfPins);
        return new NormalFrame(frameNumber, new NormalThrown(pins));
    }

    @Override
    public boolean isFinished() {
        if (getScore() == 10) {
            return true;
        }
        return false;
    }

    @Override
    public int getScore() {
        return norMalThrown.getScore();
    }

    private void valid(int frameNumber) {
        if (frameNumber < MIN_FRAMES || frameNumber > MAX_FRAMES) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public Frame bowl(int countOfPins) {
        norMalThrown.bowl(countOfPins);
        return this;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public int firstPins() {
        return norMalThrown.firstPins();
    }

    @Override
    public int secondPins() {
        return norMalThrown.secondPins();
    }

    @Override
    public boolean hasTurn() {
        return norMalThrown.hasTurn();
    }
}
