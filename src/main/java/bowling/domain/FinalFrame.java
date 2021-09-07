package bowling.domain;

public class FinalFrame implements Frame {

    private static final int FINAL_ROUND = 2;

    private final int round;
    private final Pins pins;
    private final boolean canNext;

    public FinalFrame(int round, Pins pins, boolean canNext) {
        this.round = round;
        this.pins = pins;
        this.canNext = canNext;
    }

    public static Frame create() {
        return new FinalFrame(0, Pins.create(), false);
    }

    @Override
    public Frame roll(int downPins) {
        if (hasNextRound()) {
            throw new IllegalArgumentException("마지막 라운드가 이미 끝나서 더 이상 진행 할 수 없습니다.");
        }

        if (!isNextRound()) {
            throw new IllegalArgumentException("1,2투구내에 핀을 다 쳐리 못해서 3라운드를 진행 할 수 없습니다.");
        }

        pins.roll(downPins);

        if (!pins.isAllDown()) {
            return new FinalFrame(round + 1, pins, false);
        }

        return new FinalFrame(round + 1, Pins.create(), true);
    }

    private boolean hasNextRound() {
        return round > FINAL_ROUND;
    }

    private boolean isNextRound() {
        return round != FINAL_ROUND || canNext;
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
