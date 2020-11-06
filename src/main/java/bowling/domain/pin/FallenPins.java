package bowling.domain.pin;

public class FallenPins implements Pins {
    private final int fallen;

    public FallenPins(final int fallen) {
        if (fallen < 0 || fallen > PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀 개수가 잘못 되었습니다. %d", fallen));
        }
        this.fallen = fallen;
    }

    @Override
    public int count() {
        return fallen;
    }
}
