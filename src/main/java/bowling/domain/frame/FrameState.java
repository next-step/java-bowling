package bowling.domain.frame;

import bowling.domain.roll.RollRecord;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum FrameState {

    OPEN(0, record -> !record.isStrike() && !record.isSpare()),

    STRIKE(2, RollRecord::isStrike),

    SPARE(1, RollRecord::isSpare);

    private final int countOfBonus;
    private final Predicate<RollRecord> stateMatch;

    FrameState(int countOfBonus, Predicate<RollRecord> stateMatch) {
        this.countOfBonus = countOfBonus;
        this.stateMatch = stateMatch;
    }

    public int getCountOfBonus() {
        return countOfBonus;
    }

    private boolean test(RollRecord record) {
        return stateMatch.test(record);
    }

    public static FrameState valueOf(RollRecord record) {
        return Stream.of(values())
                .filter(frameState -> frameState.test(record))
                .findFirst()
                .orElse(OPEN);
    }
}
