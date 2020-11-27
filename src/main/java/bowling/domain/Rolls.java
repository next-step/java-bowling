package bowling.domain;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Rolls {
    private final ArrayList<Roll> rolls = new ArrayList<>();

    void add(Roll roll) {
        rolls.add(roll);
    }

    int size() {
        return rolls.size();
    }

    Roll last() {
        return rolls.get(size() - 1);
    }

    int sum(int startIdx, int offset) {
        int endIdx = startIdx + offset;
        if (startIdx < 0 || offset < 0 || endIdx > rolls.size()) {
            return -1;
        }
        return Roll.sum(
                IntStream.range(startIdx, offset)
                        .mapToObj(rolls::get)
                        .collect(toList())
        );
    }
}
