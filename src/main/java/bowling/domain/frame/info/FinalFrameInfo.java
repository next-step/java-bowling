package bowling.domain.frame.info;

import java.util.Objects;

public class FinalFrameInfo extends FrameInfo {

    private static final int NEXT_ROUND = 1;
    private static final int LAST_FRAME_NUMBER = 9;
    private static final int LAST_ROUND = 2;

    private final boolean bonusRound;

    private FinalFrameInfo(int frameNumber, int round, boolean bonusRound) {
        super(frameNumber, round);
        this.bonusRound = bonusRound;
    }

    public static FinalFrameInfo of(int frameNumber, int round, boolean bonusRound) {
        return new FinalFrameInfo(frameNumber, round, bonusRound);
    }

    public static FinalFrameInfo create() {
        return of(LAST_FRAME_NUMBER, START_ROUND, false);
    }

    public FinalFrameInfo nextRoundWithBonusRound() {
        return of(this.frameNumber(), this.round() + NEXT_ROUND, true);
    }

    @Override
    public FinalFrameInfo nextFrame() {
        throw new IllegalArgumentException("Final Frame의 Next Frame은 없습니다.");
    }

    @Override
    public FinalFrameInfo nextRound() {
        return of(this.frameNumber(), this.round() + SECOND_ROUND, bonusRound);
    }

    @Override
    public boolean isSecondRound() {
        return this.round() == SECOND_ROUND;
    }

    @Override
    public boolean hasNextRound() {
        return this.bonusRound;
    }

    @Override
    public boolean isFrameEnd() {
        return this.round() >= LAST_ROUND;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FinalFrameInfo that = (FinalFrameInfo) o;
        return bonusRound == that.bonusRound;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusRound);
    }

}
