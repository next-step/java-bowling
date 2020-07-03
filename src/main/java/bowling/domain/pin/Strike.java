package bowling.domain.pin;

public class Strike extends FramePins {
    private Strike(Pins firstPins) {
        super(firstPins, Pins.of(0));
    }

    public static Strike of(Pins firstPins) {
        validateStrike(firstPins);
        return new Strike(firstPins);
    }

    private static void validateStrike(Pins firstPins) {
        if (!firstPins.equals(Pins.of(MAX_PINS_PER_FRAME))) {
            throw new IllegalArgumentException("스트라이크의 투구는 반드시 " + MAX_PINS_PER_FRAME + " 개 입니다.");
        }
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String toString() {
        return " " + super.firstPins.toString() + " ";
    }
}
