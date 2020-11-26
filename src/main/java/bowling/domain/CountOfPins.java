package bowling.domain;

import bowling.dto.CountOfPinsDto;
import bowling.exception.BadCountOfPinsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static bowling.assets.Const.PIN_NUM;

public class CountOfPins {
    private static final Map<Integer, CountOfPins> map = new HashMap();

    static {
        IntStream.rangeClosed(0, PIN_NUM)
                .forEach(count -> map.put(count, new CountOfPins(count)));
    }

    private final int count;

    private CountOfPins(int count) {
        this.count = count;
    }

    public static CountOfPins of(int count) {
        validate(count);
        return map.get(count);
    }

    private static void validate(int count) {
        int min = 0;
        int max = PIN_NUM;
        if (count < min || count > max) {
            throw BadCountOfPinsException.getInstance();
        }
    }

    public CountOfPins sum(CountOfPins countOfPins) {
        return of(count + countOfPins.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountOfPins that = (CountOfPins) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    public CountOfPinsDto exportCountOfPinsDto() {
        return new CountOfPinsDto(count);
    }
}
