package bowling.domain;

import bowling.dto.RollsDto;
import bowling.exception.RollsOutOfRangeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Rolls {
    // NOTE: get 에서의 bigO 를 1로 보장하기 위해, ArrayList 사용
    private final List<Roll> rolls = new ArrayList<>();

    void add(Roll roll) {
        rolls.add(roll);
    }

    public int size() {
        return rolls.size();
    }

    public int sum(int startIdx, int offset) {
        int endIdx = startIdx + offset;
        boolean isOutOfRange = startIdx < 0 || offset < 0 || endIdx > rolls.size();
        if (isOutOfRange) {
            throw new RollsOutOfRangeException("rolls 의 범위를 벗어난 index 입니다.");
        }
        return IntStream.range(startIdx, endIdx)
                .mapToObj(rolls::get)
                .map(Roll::getCountOfPins)
                .reduce(0, Integer::sum);
    }

    RollsDto exportRollsDto() {
        return rolls.stream()
                .map(Roll::exportRollDto)
                .collect(collectingAndThen(toList(), RollsDto::new));
    }
}
