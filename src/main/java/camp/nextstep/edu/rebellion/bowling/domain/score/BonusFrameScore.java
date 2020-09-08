package camp.nextstep.edu.rebellion.bowling.domain.score;

public class BonusFrameScore extends FrameScore {
    private static final int MAX_BONUS_FRAME_SCORES = 20;

    private BonusFrameScore() {
        super();
    }

    public static FrameScore clear() {
        return new BonusFrameScore();
    }

    @Override
    protected void checkScoreRange(int addedHits) {
        if (MAX_BONUS_FRAME_SCORES < first.getHits() + addedHits) {
            throw new IllegalArgumentException("최대 20 점을 넘을 수 없습니다");
        }
    }
}
