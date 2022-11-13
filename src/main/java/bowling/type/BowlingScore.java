package bowling.type;

import bowling.domain.GeneralScore;

public enum BowlingScore {
    STRIKE,
    SPARE,
    MISS,
    GUTTER,
    NONE;

    public static BowlingScore from(GeneralScore generalScore) {
        if(generalScore.isStrike()) return BowlingScore.STRIKE;
        if(generalScore.size() == 1) return BowlingScore.NONE;
        if(generalScore.getFirst() + generalScore.getSecond() == 10) return BowlingScore.SPARE;
        if(generalScore.getSecond() == 0) return BowlingScore.GUTTER;
        return BowlingScore.MISS;
    }
}
