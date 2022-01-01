package bowling.domain;

import java.util.Collections;

import bowling.engine.BonusScores;
import bowling.engine.Score;
import bowling.engine.collection.FirstClassMutableList;

public class ClearBonusScores extends FirstClassMutableList<Score> implements BonusScores {
    private static final int ACCUMULATION_BASE = 0;
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
    public void appendBonus(Score score) {
        if (!completed()) {
            append(score);
        }
    }

    @Override
    public Score sum() {
        return FrameScore.of(stream().map(Score::toInt)
                .reduce(ACCUMULATION_BASE, Integer::sum));
    }

    @Override
    public boolean remain() {
        return size() < limit;
    }

    @Override
    public boolean completed() {
        return size() >= limit;
    }
}
