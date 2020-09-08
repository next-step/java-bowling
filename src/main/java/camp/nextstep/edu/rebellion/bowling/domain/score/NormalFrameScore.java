package camp.nextstep.edu.rebellion.bowling.domain.score;

public class NormalFrameScore extends FrameScore {
    private static final int MAX_FRAME_SCORES = 10;

    private NormalFrameScore() {
        super();
    }

    public static FrameScore clear() {
        return new NormalFrameScore();
    }

    @Override
    protected void checkScoreRange(int addedHits) {
        if (MAX_FRAME_SCORES < first.getHits() + addedHits) {
            throw new IllegalArgumentException("최대 10 점을 넘을 수 없습니다");
        }
    }
}
