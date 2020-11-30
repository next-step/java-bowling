package bowling.domain;

import bowling.dto.RollsDto;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class Rolls {
    // NOTE: get 에서의 bigO 를 1로 보장하기 위해, ArrayList 사용
    private final ArrayList<Roll> rolls = new ArrayList<>();

    void add(Roll roll) {
        rolls.add(roll);
    }

    int size() {
        return rolls.size();
    }

    int sum(int startIdx, int offset) {
        int endIdx = startIdx + offset;
        if (startIdx < 0 || offset < 0 || endIdx > rolls.size()) {
            return -1;
        }
        return Roll.sum(
                IntStream.range(startIdx, endIdx)
                        .mapToObj(rolls::get)
                        .collect(toList())
        );
    }

    RollsDto exportRollsDto() {
        return rolls.stream()
                .map(Roll::exportRollDto)
                .collect(collectingAndThen(toList(), RollsDto::new));
    }
}
