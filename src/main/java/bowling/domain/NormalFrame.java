package bowling.domain;

public class NormalFrame implements Frame {

    public static final int FIRST_ROUND = 0;
    private static final int MAX_NORMAL_FRAME = 8;


    private final int frameNumber;
    private final int round;
    private final Pins pins;

    private NormalFrame(int frameNumber, int round, Pins pins) {
        this.frameNumber = frameNumber;
        this.round = round;
        this.pins = pins;
    }

    public static NormalFrame of(int frameNumber, int round, Pins pins) {
        return new NormalFrame(frameNumber, round, pins);
    }

    public static NormalFrame create() {
        return new NormalFrame(0, 0, Pins.create());
    }


    @Override
    public Frame roll(int downPins) {
        Pins roll = pins.roll(downPins);

        if (isLastFrame()) {
            return new FinalFrame(0, Pins.create(), false);
        }

        if (hasNextFrame()) {
            return new NormalFrame(frameNumber + 1, 0, Pins.create());
        }

        return new NormalFrame(frameNumber, round + 1, roll);
    }

    private boolean isLastFrame() {
        return frameNumber == MAX_NORMAL_FRAME && (round == 1 || pins.numberOfPinDowns() == 10);
    }

    private boolean hasNextFrame() {
        return round == 1 || pins.isAllDown();
    }

    @Override
    public int numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    @Override
    public int currentFrame() {
        return frameNumber;
    }

    @Override
    public boolean hasNextRound() {
        return pins.isAllDown() || round == FIRST_ROUND;
    }

    @Override
    public Status pinStatus() {
        return pins.status();
    }

}
