package bowling.domain;

import java.util.Objects;

public interface PinMarkSymbol {

    String getSymbol();

    PinMarkSymbol Strike = SpecialPinMarkSymbol.Strike;
    PinMarkSymbol Spare = SpecialPinMarkSymbol.Spare;
    PinMarkSymbol Gutter = SpecialPinMarkSymbol.Gutter;

    static PinMarkSymbol from(PinMark pinMark) {
        return from(pinMark.getCountOfFallDownPins());
    }

    static PinMarkSymbol from(int pins) {
        if (pins < 0) throw new IllegalArgumentException("표시할 수 있는 기호가 없습니다");

        if (pins == 0) return Gutter;
        if (pins == PinMark.MAX_PINS) return Strike;
        return new NumberPinMarkSymbol(pins);
    }
}

class NumberPinMarkSymbol implements PinMarkSymbol {
    private String symbol;

    NumberPinMarkSymbol(int symbol) {
        this.symbol = String.valueOf(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberPinMarkSymbol that = (NumberPinMarkSymbol) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}

enum SpecialPinMarkSymbol implements PinMarkSymbol {
    Strike("X"), Spare("/"), Gutter("-");

    private String symbol;

    SpecialPinMarkSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}

