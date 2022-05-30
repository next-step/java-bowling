package bowling.domain.bowl.type;

import bowling.domain.bowl.Gutter;
import bowling.domain.bowl.Miss;
import bowling.domain.bowl.Spare;
import bowling.domain.bowl.Strike;
import bowling.domain.bowl.First;
import bowling.domain.bowl.Ready;
import bowling.domain.score.Scores;
import java.util.Arrays;
import java.util.List;

public enum BowlType {
    GUTTER(Gutter::checkType),
    MISS(Miss::checkType),
    SPARE(Spare::checkType),
    STRIKE(Strike::checkType),
    FIRST(First::checkType),
    READY(Ready::checkType);

    private final Condition condition;

    BowlType(Condition condition) {
        this.condition = condition;
    }

    private static BowlType getType(Scores scores) {
        return Arrays.stream(BowlType.values())
                .filter(type -> type.condition.check(scores))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(scores+": bowl type을 구할 수 없습니다."));
    }

    public static BowlType getType(List<Integer> scoreList) {
        Scores scores = new Scores(scoreList);
        return getType(scores);
    }
}
