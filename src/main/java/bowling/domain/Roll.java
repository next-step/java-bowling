package bowling.domain;

import bowling.exception.BadCountOfPinsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.assets.Const.PIN_NUM;
import static java.util.stream.Collectors.toList;

public class Roll {
    private static final Map<Integer, Roll> map = new HashMap();

    static {
        IntStream.rangeClosed(0, PIN_NUM)
                .forEach(count -> map.put(count, new Roll(count)));
    }

    private final int countOfPins;

    private Roll(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    public static Roll of(int count) {
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

    static int sum(List<Roll> rolls) {
        return rolls.stream()
                .map(roll -> roll.countOfPins)
                .reduce(0, Integer::sum);
    }

    static List<Integer> toIntegers(List<Roll> rolls) {
        return rolls.stream()
                .map(roll -> roll.countOfPins)
                .collect(toList());
    }
}
