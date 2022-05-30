package bowling.domain.bowl.type;

import bowling.domain.bowl.Gutter;
import bowling.domain.bowl.Miss;
import bowling.domain.bowl.Spare;
import bowling.domain.bowl.Strike;
import bowling.domain.bowl.First;
import bowling.domain.bowl.Ready;
import bowling.domain.bowl.Bowl;
import bowling.domain.score.Scores;
import java.util.Arrays;
import java.util.List;

public enum BowlType {
    GUTTER(Gutter::checkType, Gutter::new),
    MISS(Miss::checkType, Miss::new),
    SPARE(Spare::checkType, Spare::new),
    STRIKE(Strike::checkType, Strike::new),
    FIRST(First::checkType, First::new),
    READY(Ready::checkType, Ready::new);

    private final Condition condition;
    private final Creation creation;

    BowlType(Condition condition, Creation creation) {
        this.condition = condition;
        this.creation = creation;
    }

    public static BowlType getType(Scores scores) {
        return Arrays.stream(BowlType.values())
                .filter(type -> type.condition.check(scores))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(scores+": bowl type을 구할 수 없습니다."));
    }

    public static BowlType getType(List<Integer> scoreList) {
        Scores scores = new Scores(scoreList);
        return getType(scores);
    }

    public Bowl create(Scores scores){
        return creation.create(scores);
    }
}
