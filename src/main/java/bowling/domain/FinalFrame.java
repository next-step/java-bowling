package bowling.domain;

public class FinalFrame implements Frame {

    public static final int MAX_FINAL_ROUND = 2;
    public static final int SECOND_ROUND = 1;
    public static final int BOWLING_LAST_FRAME = 9;
    public static final int NEXT_FRAME = 1;

    private final int round;
    private final Pins pins;
    private final boolean bonusRound;

    public FinalFrame(int round, Pins pins, boolean bonusRound) {
        this.round = round;
        this.pins = pins;
        this.bonusRound = bonusRound;
    }

    @Override
    public Frame roll(int downPins) {
        validate();
        Pins downedPins = pins.roll(downPins);

        if (downedPins.isStrike() || downedPins.isSpare() || bonusRound) {
            return new FinalFrame(round + NEXT_FRAME, Pins.create(), true);
        }

        return new FinalFrame(round + NEXT_FRAME, Pins.from(pins), false);
    }

    private void validate() {
        if (round == MAX_FINAL_ROUND && !bonusRound) {
            throw new IllegalArgumentException("스페어처리를 못하여서 3라운드를 진행 할 수 없습니다.");
        }
    }

    @Override
    public boolean hasNextRound() {
        return round <= SECOND_ROUND || (round == 2 && bonusRound);
    }

    @Override
    public Status pinStatus() {
        return pins.status();
    }

    @Override
    public int numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    @Override
    public int currentFrame() {
        return BOWLING_LAST_FRAME;
    }

}
