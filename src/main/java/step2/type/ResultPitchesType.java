package step2.type;

import step2.domain.Condition;
import step2.domain.Extract;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ResultPitchesType {
    STRIKE((first, second)-> first == 10),
    SPARE((first, second)-> (first != 10) && (first + second == 10)),
    MISS((first, second)->(first != 10) && (first + second != 10));

    private final Condition condition;

    ResultPitchesType(Condition condition) {
        this.condition = condition;
    }

    public static ResultPitchesType getType(int firstPoint, int secondPoint) {
        return Arrays.stream(ResultPitchesType.values())
                .filter(value->value.condition.filter(firstPoint, secondPoint))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
