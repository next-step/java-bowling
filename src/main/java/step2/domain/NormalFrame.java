package step2.domain;

import step2.exception.OutOfRangeFrameException;
import step2.strategy.PitchesStrategy;

import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int MAXIMUM_NORMAL_FRAME_RANGE = 9;

    private final int frameNo;
    private int remainingCount;
    private int firstPoint;
    private int secondPoint;

    private final Frame prev;
    private final Frame next;

    public NormalFrame(int frameNo, Frame frame) {
        this.frameNo = frameNo;
        firstPoint = 0;
        secondPoint = 0;
        remainingCount = 2;
        this.prev = frame;
        this.next = frameNo < MAXIMUM_NORMAL_FRAME_RANGE ?makeNext() : null;
    }

    public static NormalFrame from(int frameNo) {
        return new NormalFrame(frameNo, null);
    }



    @Override
    public void pitches(PitchesStrategy strategy) {
        if (remainingCount == 2) {
            remainingCount--;
            firstPoint = strategy.shot(0);
        }
        if (remainingCount == 1) {
            remainingCount--;
            secondPoint = strategy.shot(firstPoint);
        }
    }

    @Override
    public int getScore() {
        return 0;
    }

    private boolean existsNext() {
        return Objects.nonNull(next);
    }

    private boolean existsPrev() {
        return Objects.nonNull(prev);
    }


    @Override
    public Frame next() {
        return null;
    }

    @Override
    public void linkNext(Frame frame) {

    }

    @Override
    public Frame makeNext() {
        if (frameNo >= 9) {
          throw new OutOfRangeFrameException("더이상 생성할 수 없습니다.");
        }
        return new NormalFrame(frameNo+1, this);
    }
}
