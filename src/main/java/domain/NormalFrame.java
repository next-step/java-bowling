package domain;

import View.BowlingFrame;

import java.util.Optional;
import java.util.stream.IntStream;

import static domain.BowlingGame.TOTAL_FRAME_COUNT;

public class NormalFrame implements BowlingFrame {
    protected static final int STRIKE_CHECKING_POINTS = 2;
    protected static final int SPARE_CHECKING_POINTS = 1;
    protected static final int NEXT = 1;
    protected static final int ONE = 1;
    protected static final int NO_MORE_NEXT = -1;

    private NormalScore normalScore;
    public NormalFrame next;

    public NormalFrame(int frameNumber) {
        this.normalScore = new NormalScore(frameNumber);
        this.next = null;
    }

    @Override
    public boolean doBowling(int point) {
        if (nowBowlable()) {
            normalScore.bowl(point);
            return true;
        }
        return false;
    }

    @Override
    public int sumScore() {
        return normalScore.sumScore();
    }

    @Override
    public String getResult() {
        return normalScore.getResult();
    }

    public boolean nowBowlable() {
        return normalScore.nowBowlable();
    }

    public NormalFrame nextFrame() {
        next = new NormalFrame(normalScore.getFrameNumber() + NEXT);
        return next;
    }

    public int getNextFrameNumber() {
        int scoreNumber = normalScore.getFrameNumber();
        return nowBowlable() ? scoreNumber : scoreNumber + NEXT;
    }

    public int framePoint(FinalFrame finalFrame) {
        if (getNextFrameNumber() < TOTAL_FRAME_COUNT && normalScore.nowBowlable()) {
            return NO_MORE_NEXT;
        }
        if (normalScore.isStrike()) {
            int result = sumIfStrike(finalFrame);
            return result == NO_MORE_NEXT ? result : result + sumScore();
        }
        if (normalScore.isSpare()) {
            int result = sumIfSpare(finalFrame);
            return result == NO_MORE_NEXT ? result : result + sumScore();
        }
        return sumScore();
    }

    private int sumIfStrike(FinalFrame finalFrame) {
        if (getNextFrameNumber() == TOTAL_FRAME_COUNT && (finalFrame.getPointSize() >= STRIKE_CHECKING_POINTS)) {
            return finalFrame.isStart() ? finalFrame.framePoint(STRIKE_CHECKING_POINTS) : NO_MORE_NEXT;
        }

        Optional<NormalFrame> maybeNextNormalFrame = Optional.ofNullable(next);
        if (!maybeNextNormalFrame.isPresent()) {
            return NO_MORE_NEXT;
        }

        NormalScore nextNormalScore = maybeNextNormalFrame.get().normalScore;
        if (!nextNormalScore.isStrike() && !nextNormalScore.isNullablePoint(NormalScore.SECOND)) {
            return sumPoint(nextNormalScore, STRIKE_CHECKING_POINTS);
        }

        Optional<NormalFrame> maybeThirdNormalFrame = Optional.ofNullable(maybeNextNormalFrame.get().next);
        if (nextNormalScore.isStrike() && (maybeThirdNormalFrame.isPresent() || finalFrame.isStart())) {
            int secondScore = nextNormalScore.sumScore();
            int thirdScore = maybeThirdNormalFrame.isPresent() ? maybeThirdNormalFrame.get().sumScore() : finalFrame.framePoint(ONE);
            return secondScore + thirdScore;
        }

        return NO_MORE_NEXT;
    }

    private int sumIfSpare(FinalFrame finalFrame) {
        if (getNextFrameNumber() == TOTAL_FRAME_COUNT) {
            return finalFrame.isStart() ? finalFrame.framePoint(SPARE_CHECKING_POINTS) : NO_MORE_NEXT;
        }

        Optional<NormalFrame> maybeNextNormalFrame = Optional.ofNullable(next);
        if (!maybeNextNormalFrame.isPresent()) {
            return NO_MORE_NEXT;
        }

        NormalScore nextNormalScore = maybeNextNormalFrame.get().normalScore;
        return sumPoint(nextNormalScore, SPARE_CHECKING_POINTS);
    }

    private int sumPoint(NormalScore nextNormalScore, int count) {
        return IntStream.range(0, count)
                .boxed()
                .mapToInt(n -> nextNormalScore.getPoint(n))
                .sum();
    }
}
