package bowling.domain;

public class NormalFrame extends Frame {
    private final int frameNumber;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public Frame execute(final BowlingPins bowlingPins) {
        if (record.isEmpty()) {
            record.put(Round.FIRST_ROUND, bowlingPins);
            if (bowlingPins.isMax()) {
                isFinished = true;
                if (this.frameNumber == 9) {
                    return new FinalFrame();
                }
                return this.next();
            }
            return this;
        }
        if (this.record.get(Round.FIRST_ROUND).isNotAddable(bowlingPins)) {
            throw new IllegalArgumentException("쓰러트리는 볼링핀의 수의 합은 10이하여야합니다");
        }
        isFinished = true;
        record.put(Round.SECOND_ROUND, bowlingPins);
        return this.next();
    }

    @Override
    public int getFrameNumber() {
        return this.frameNumber;
    }

    private NormalFrame next() {
        return new NormalFrame(this.frameNumber + 1);
    }

}
