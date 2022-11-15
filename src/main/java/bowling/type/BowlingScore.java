package bowling.type;

import bowling.domain.Scores;

public enum BowlingScore {
    STRIKE,
    SPARE,
    MISS,
    GUTTER,
    NONE;

    public static BowlingScore from(Scores scores) {
        if (scores.isStrike()) return BowlingScore.STRIKE;
        if (scores.size() == 1) return BowlingScore.NONE;
        if (scores.getFirst() + scores.getSecond() == 10) return BowlingScore.SPARE;
        if (scores.getSecond() == 0) return BowlingScore.GUTTER;
        return BowlingScore.MISS;
    }
}
