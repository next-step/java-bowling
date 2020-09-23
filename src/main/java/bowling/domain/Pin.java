package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MAX_PIN_COUNT = 10;

    private final int count;
    private final ScoreSymbol symbol;

    public Pin(int count) {
        this(count, count, true);
    }

    private Pin(int count, int totalCount) {
        this(count, totalCount, false);
    }

    private Pin(int count, int totalCount, boolean isFirst) {
        this.validate(count, totalCount);

        this.count = count;
        this.symbol = initSymbol(totalCount, isFirst);
    }

    public ScoreSymbol initSymbol(int count, boolean isFirst) {
        return ScoreSymbol.valueOf(count, isFirst);
    }

    private void validate(int count, int totalCount) {
        if (count < 0) {
            throw new IllegalArgumentException("쓰러트린 핀의 개수는 0이상 이어야 합니다.");
        }

        if (count > MAX_PIN_COUNT || totalCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("쓰러트린 핀의 개수는 10이하 이어야 합니다.");
        }
    }

    public Pin next(int nextCount) {
        return new Pin(nextCount, count + nextCount);
    }

    public boolean isEnd() {
        return ScoreSymbol.tenScore.contains(this.symbol);
    }

    public String getSymbolValue() {
        if (getSymbol().equals(ScoreSymbol.MISS)) {
            return String.valueOf(count);
        }

        return symbol.getValue();
    }

    public ScoreSymbol getSymbol() {
        return symbol;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
