package bowling.domain;

public class FinalFrame implements Frame {

    private static final int FINAL_ROUND = 2;

    private final int round;
    private final Pins pins;
    private final boolean bonusStage;

    public FinalFrame(int round, Pins pins, boolean bonusStage) {
        this.round = round;
        this.pins = pins;
        this.bonusStage = bonusStage;
    }

    public static Frame create() {
        return new FinalFrame(0, Pins.create(), false);
    }

    public static Frame of(int round, Pins pins, boolean bonusStage) {
        return new FinalFrame(round, pins, bonusStage);
    }

    @Override
    public Frame roll(int downPins) {
        if (!hasNextFrame()) {
            throw new IllegalArgumentException("마지막 라운드가 이미 끝나서 더 이상 진행 할 수 없습니다.");
        }

        if (!hasBonusFrame()) {
            throw new IllegalArgumentException("1,2투구내에 핀을 다 쳐리 못해서 3라운드를 진행 할 수 없습니다.");
        }

        pins.roll(downPins);

        if (!pins.isAllDown()) {
            return FinalFrame.of(round + 1, Pins.from(pins), bonusStage);
        }

        return FinalFrame.of(round + 1, Pins.create(), true);
    }

    private boolean hasBonusFrame() {
        return round != FINAL_ROUND || bonusStage;
    }

    @Override
    public boolean hasNextFrame() {
        return round <= FINAL_ROUND;
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


