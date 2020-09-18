package bowling.domain;

public class FinalFrame extends Frame {

    private final String FINAL_FRAME_RANGE = "FinalFrame의 인덱스는 10이여야합니다.";

    private RollingResult finalRollingResult = new FinalRollingResult();

    public FinalFrame(int frameIndex) {
        super(frameIndex);
    }

    @Override
    protected void validationFrameIndex(int frameIndex) {
        if (frameIndex != MAX_FRAME_INDEX) {
            throw new IllegalArgumentException(FINAL_FRAME_RANGE);
        }
    }

    @Override
    public boolean rollingEnd() {
        return finalRollingResult.isFinish();
    }

    @Override
    void bowl(Pin pin) {
        finalRollingResult.bowl(pin);
    }

    @Override
    public boolean isEndAllFrame() {
        return finalRollingResult.isFinish();
    }

    @Override
    public String index() {
        return String.valueOf(MAX_FRAME_INDEX);
    }

    @Override
    public String currentFrameStatus() {
        return finalRollingResult.currentFrameStatus();
    }
}
