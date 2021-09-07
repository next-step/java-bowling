package bowling.domain;

public class NormalFrame implements Frame {

    public static final int MAX_NORMAL_FRAME = 2;
    private final int round;
    private final Pins pins;

    private NormalFrame(int round, Pins pins) {
        this.round = round;
        this.pins = pins;
    }

    public static Frame create() {
        return new NormalFrame(0, Pins.create());
    }

    @Override
    public Frame roll(int downPins) {
        if (round == MAX_NORMAL_FRAME) {
            throw new IllegalArgumentException("이번 프레임은 끝났습니다 다음 프레임을 진행해주세요.");
        }

        pins.roll(downPins);

        if (!pins.isAllDown()) {
            return new NormalFrame(round + 1, pins);
        }

        return this;
    }

    @Override
    public int round() {
        return round;
    }

    @Override
    public Pins pins() {
        return pins;
    }

}
