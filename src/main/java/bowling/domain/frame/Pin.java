package bowling.domain.frame;

import bowling.dto.PinDto;
import bowling.exception.PinException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.asset.Const.MAX_PIN_NUMBER;

public class Pin {
    private static final Map<Integer, Pin> map = new HashMap<>();

    static {
        IntStream.rangeClosed(0, MAX_PIN_NUMBER)
                .forEach(count -> map.put(count, new Pin(count)));
    }

    private final int countOfDownPins;

    private Pin(int countOfDownPins) {
        this.countOfDownPins = countOfDownPins;
    }

    public static Pin of(int count) {
        if (count < 0 || count > MAX_PIN_NUMBER) {
            throw new PinException("핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        return map.get(count);
    }

    int sum(int countOfDownPins) {
        return countOfDownPins + this.countOfDownPins;
    }

    PinDto exportPinDto() {
        return new PinDto(countOfDownPins);
    }
}
