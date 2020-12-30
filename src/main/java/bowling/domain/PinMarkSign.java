package bowling.domain;

import java.util.Objects;

public interface PinMarkSign {

    String toString();

    PinMarkSign Strike = SpecialPinMarkSign.Strike;
    PinMarkSign Spare = SpecialPinMarkSign.Spare;
    PinMarkSign Gutter = SpecialPinMarkSign.Gutter;

    static PinMarkSign number(int pins){
        if( pins < 0 ) throw new IllegalArgumentException("표시할 수 있는 기호가 없습니다");

        if( pins == 0 ) return Gutter;
        if( pins == PinMark.MAX_PINS ) return Strike;
        return new NumberPinMarkSign(pins);
    }
}

class NumberPinMarkSign implements PinMarkSign {
    private String sign;

    NumberPinMarkSign(int sign) {
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
        NumberPinMarkSign that = (NumberPinMarkSign) o;
        return Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sign);
    }
}

enum SpecialPinMarkSign implements PinMarkSign {
    Strike("X"), Spare("/"), Gutter("-");

    private String sign;

    SpecialPinMarkSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}

