package bowling.domain;

public class NormalFrame extends Frame {

    private final String NORMAL_FRAME_RANGE = "NormalFrame의 범위는 1~9 사이입니다.";

    private RollingResult normalRollingResult = new NormalRollingResult();

    public NormalFrame(int frameIndex) {
        super(frameIndex);
    }

    @Override
    protected void validationFrameIndex(int frameIndex) {
        if (frameIndex < MIN_FRAME_INDEX || frameIndex > MAX_FRAME_INDEX - 1) {
            throw new IllegalArgumentException(NORMAL_FRAME_RANGE);
        }
    }

    @Override
    public boolean rollingEnd() {
        return normalRollingResult.isFinish();
    }

    @Override
    void bowl(Pin pin) {
        normalRollingResult.bowl(pin);
    }

    @Override
    public boolean isEndAllFrame() {
        return false;
    }

    @Override
    public String index() {
        return String.valueOf(frameIndex);
    }

    @Override
    public String currentFrameStatus() {
        return normalRollingResult.currentFrameStatus();
    }
}
