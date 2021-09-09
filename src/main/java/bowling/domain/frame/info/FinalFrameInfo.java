package bowling.domain.frame.info;

public class FinalFrameInfo implements FrameInfo {


    private static final int MAX_FINAL_ROUND = 2;
    private static final int SECOND_ROUND = 1;
    private static final int LAST_FRAME_NUMBER = 9;

    private final int frameNumber;
    private final int round;
    private final boolean bonusRound;

    private FinalFrameInfo(int frameNumber, int round, boolean bonusRound) {
        this.frameNumber = frameNumber;
        this.round = round;
        this.bonusRound = bonusRound;
    }

    public static FinalFrameInfo of(int frameNumber, int round, boolean bonusFrame) {
        return new FinalFrameInfo(frameNumber, round, bonusFrame);
    }

    public static FinalFrameInfo create() {
        return of(0, 0, false);
    }

    @Override
    public FinalFrameInfo nextFrame() {
        throw new IllegalArgumentException("Final Frame의 Next Round는 없습니다.");
    }

    @Override
    public FinalFrameInfo nextRound() {
        return of(frameNumber, round + 1, bonusRound);
    }

    public FinalFrameInfo nextRoundWithBonusRound() {
        return of(frameNumber, round + 1, true);
    }

    @Override
    public boolean isEndFrame() {
        return frameNumber == LAST_FRAME_NUMBER;
    }

    @Override
    public boolean isLastRound() {
        return round == MAX_FINAL_ROUND;
    }

    @Override
    public boolean isFirstRound() {
        return round == FIRST_ROUND;
    }

    public boolean hasBonusRound() {
        return bonusRound;
    }

    public boolean hasNextRound() {
        return round <= SECOND_ROUND || (round == 2 && bonusRound);
    }
}
