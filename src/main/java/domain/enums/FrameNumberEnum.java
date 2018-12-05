package domain.enums;

public enum FrameNumberEnum {

    NEXT_DEFAULT_NUMBER(1),
    FINAL_FRAME_NUMBER(10),
    EXTRA_FRAME_NUMBER(11),
    FINISH_FRAME_NUMBER(12);

    private int frameNumber;

    FrameNumberEnum(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public boolean isEqual(int frameNumber) {
        if (this.frameNumber == frameNumber) {
            return true;
        }
        return false;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int addFrameNumber(int frameNumber) {
        return this.frameNumber + frameNumber;
    }
}
