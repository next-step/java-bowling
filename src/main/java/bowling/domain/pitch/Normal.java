package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public final class Normal implements Pitch {
    private static final int MAX_KNOCKED_PINS_COUNT = 9;
    private static final String OVER_MAX_KNOCKED_PINS_COUNT_MESSAGE =
            String.format("Normal의 쓰러뜨린 핀 개수는 %d개 이하여야 합니다.", MAX_KNOCKED_PINS_COUNT);
    private static final int STRIKE_PINS_COUNT = 10;

    private final KnockedPins knockedPins;

    public Normal(final int knockedPinsCount) {
        this(KnockedPins.from(knockedPinsCount));
    }

    public Normal(final KnockedPins knockedPins) {
        validateMaxKnockedPins(knockedPins);
        this.knockedPins = knockedPins;
    }

    private void validateMaxKnockedPins(final KnockedPins knockedPins) {
        if (knockedPins.count() > MAX_KNOCKED_PINS_COUNT) {
            throw new IllegalArgumentException(OVER_MAX_KNOCKED_PINS_COUNT_MESSAGE);
        }
    }

    @Override
    public KnockedPins knockedPins() {
        return knockedPins;
    }

    @Override
    public Pitch play(final int knockedPinsCount) {
        return play(KnockedPins.from(knockedPinsCount));
    }

    @Override
    public Pitch play(final KnockedPins knockedPins) {
        if (this.knockedPins.count() + knockedPins.count() == STRIKE_PINS_COUNT) {
            return new Spare(knockedPins);
        }

        return new Normal(knockedPins);
    }
}
