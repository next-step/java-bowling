package bowling.controller.dto;

public class FrameInfo {

    private final String frameNumber;
    private final String pinDownResult;

    public FrameInfo(String frameNumber, String pinDownResult) {
        this.frameNumber = frameNumber;
        this.pinDownResult = pinDownResult;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public String getPinDownResult() {
        return pinDownResult;
    }
}
