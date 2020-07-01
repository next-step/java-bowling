package bowling.domain;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME = 10;

    public FinalFrame() {
    }

    @Override
    public FinalFrame execute(final BowlingPins bowlingPins) {
        if (record.isEmpty()) {
            record.put(Round.FIRST_ROUND, bowlingPins);
            if (bowlingPins.isMax()) {
                isFinished = true;
                return this;
            }
            isFinished = false;
        }
        if (this.record.get(Round.FIRST_ROUND).isNotAddable(bowlingPins)) {
            throw new IllegalArgumentException("쓰러트리는 볼링핀의 수의 합은 10이하여야합니다");
        }
        record.put(Round.SECOND_ROUND, bowlingPins);
        isFinished = true;
        return this;
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME;
    }
}
