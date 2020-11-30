package bowling.domain;

import bowling.dto.RollDto;
import bowling.exception.RollException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.asset.Const.PIN_NUM;

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
            throw new RollException("한 프레임에서 쓰러진 핀의 개수는 0 이상 10 이하여야 합니다.");
        }
    }

    static int sum(List<Roll> rolls) {
        return rolls.stream()
                .map(roll -> roll.countOfPins)
                .reduce(0, Integer::sum);
    }

    RollDto exportRollDto() {
        return new RollDto(countOfPins);
    }
}
