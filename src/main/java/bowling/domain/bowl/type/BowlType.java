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
    GUTTER((scores -> {
        if(!scores.checkSize(Gutter.PITCH_COUNT)){
            return false;
        }
        return scores.getScoreSum() == Gutter.GUTTER_VALUE;
    }), Gutter::new),

    MISS((scores -> {
        if(!scores.checkSize(Miss.PITCH_COUNT)){
            return false;
        }
        int sum = scores.getScoreSum();
        return Miss.MIN_PIN_HIT_COUNT < sum && sum < Miss.MAX_PIN_HIT_COUNT;
    }), Miss::new),

    SPARE((scores)->{
        if(!scores.checkSize(Spare.PITCH_COUNT)){
            return false;
        }
        return scores.getScoreSum() == Spare.MAX_PIN_HIT_COUNT;
    }, Spare::new),

    STRIKE((scores -> {
        if(!scores.checkSize(Strike.PITCH_COUNT)){
            return false;
        }
        return scores.getFistScore() == Strike.STRIKE_VALUE;
    }), Strike::new),

    FIRST((scores)->{
        if (!scores.checkSize(First.PITCH_COUNT)) {
            return false;
        }
        return scores.getFistScore() != First.MAX_PIN_HIT_COUNT;
    }, First::new),

    READY((scores -> scores.checkSize(Ready.PITCH_COUNT)), Ready::new);

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
