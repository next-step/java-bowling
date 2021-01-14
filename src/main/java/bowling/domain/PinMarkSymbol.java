package bowling.domain;

import java.util.Objects;

public interface PinMarkSymbol {

    String toString();

    PinMarkSymbol Strike = SpecialPinMarkSymbol.Strike;
    PinMarkSymbol Spare = SpecialPinMarkSymbol.Spare;
    PinMarkSymbol Gutter = SpecialPinMarkSymbol.Gutter;

    static PinMarkSymbol from(int pins) {
        if (pins < 0) throw new IllegalArgumentException("표시할 수 있는 기호가 없습니다");

        if (pins == 0) return Gutter;
        if (pins == PinMark.MAX_PINS) return Strike;
        return new NumberPinMarkSymbol(pins);
    }
}

class NumberPinMarkSymbol implements PinMarkSymbol {
    private String sign;

    NumberPinMarkSymbol(int sign) {
        this.sign = String.valueOf(sign);
    }

    @Override
    public String toString() {
        return sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberPinMarkSymbol that = (NumberPinMarkSymbol) o;
        return Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sign);
    }
}

enum SpecialPinMarkSymbol implements PinMarkSymbol {
    Strike("X"), Spare("/"), Gutter("-");

    private String sign;

    SpecialPinMarkSymbol(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}

