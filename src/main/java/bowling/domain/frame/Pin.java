package bowling.domain.frame;

import bowling.dto.PinDto;
import bowling.exception.PinException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.asset.Const.PIN_NUMBER;

public class Pin {
    private static final Map<Integer, Pin> map = new HashMap<>();

    static {
        IntStream.rangeClosed(0, PIN_NUMBER)
                .forEach(count -> map.put(count, new Pin(count)));
    }

    private final int countOfPins;

    private Pin(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    public static Pin of(int count) {
        if (count < 0 || count > PIN_NUMBER) {
            throw new PinException("핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        return map.get(count);
    }

    int sum(int countOfPins) {
        return countOfPins + this.countOfPins;
    }

    PinDto exportPinDto() {
        return new PinDto(countOfPins);
    }
}
