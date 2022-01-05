package bowling.domain;

import java.util.Collections;

import bowling.engine.BonusScores;
import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.collection.FirstClassMutableList;

import static bowling.domain.BowlingScore.ACCUMULATION_BASE;

public class ClearBonusScores extends FirstClassMutableList<Score> implements BonusScores {
    private final int limit;

    protected ClearBonusScores(int limit) {
        super(Collections.emptyList());
        this.limit = limit;
    }

    public static BonusScores byNone() {
        return new ClearBonusScores(NUMBER_OF_NO_BONUS);
    }

    public static BonusScores bySpare() {
        return new ClearBonusScores(NUMBER_OF_SPARE_BONUS);
    }

    public static BonusScores byStrike() {
        return new ClearBonusScores(NUMBER_OF_STRIKE_BONUS);
    }

    @Override
    public void appendBonus(Shot shot) {
        if (remain()) {
            append(shot.score());
        }
    }

    @Override
    public Score sum() {
        return stream().reduce(ACCUMULATION_BASE, Score::add);
    }

    @Override
    public boolean remain() {
        return limit > NUMBER_OF_NO_BONUS && size() < limit;
    }
}
