package bowling.domain.state;

import bowling.domain.Pins;

public class Miss extends EndState {
    private static final String SYMBOL_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        validateFirstPinsAndSecondPins(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validateFirstPinsAndSecondPins(Pins firstPins, Pins secondPins) {
        if (!firstPins.isMiss(secondPins)) {
            throw new IllegalArgumentException(String.format("Miss 상태는 첫번쨰 투구와 두번쨰 투구로 쓰러트린 핀 합이 10개 미만 이어야 합니다. 전달 받은 쓰러뜨린 firstPins 갯수 : %s, secondPins 갯수 : %s", firstPins, secondPins));
        }
    }

    @Override
    public String symbol() {
        return String.format(SYMBOL_FORMAT, gutterOrValue(firstPins), gutterOrValue(secondPins));
    }

    private String gutterOrValue(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pins.hitPins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Miss miss = (Miss) o;

        if (!firstPins.equals(miss.firstPins)) return false;
        return secondPins.equals(miss.secondPins);
    }

    @Override
    public int hashCode() {
        int result = firstPins.hashCode();
        result = 31 * result + secondPins.hashCode();
        return result;
    }
}