package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public final class Spare implements Pitch {
    private static final int MIN_KNOCKED_PINS_COUNT = 1;
    private static final String LESS_MIN_KNOCKED_PINS_COUNT_MESSAGE =
            String.format("Spare의 쓰러뜨린 핀 개수는 %d개 이상이어야 합니다.", MIN_KNOCKED_PINS_COUNT);
    private static final int STRIKE_PINS_COUNT = 10;

    private final KnockedPins knockedPins;

    public Spare(final int knockedPinsCount) {
        this(KnockedPins.from(knockedPinsCount));
    }

    public Spare(final KnockedPins knockedPins) {
        validateMinKnockedPins(knockedPins);
        this.knockedPins = knockedPins;
    }

    private void validateMinKnockedPins(final KnockedPins knockedPins) {
        if (knockedPins.count() < MIN_KNOCKED_PINS_COUNT) {
            throw new IllegalArgumentException(LESS_MIN_KNOCKED_PINS_COUNT_MESSAGE);
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
        if (knockedPins.count() == STRIKE_PINS_COUNT) {
            return new Strike();
        }

        return new Normal(knockedPins);
    }
}
