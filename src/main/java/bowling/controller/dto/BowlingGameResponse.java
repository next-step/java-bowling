package bowling.controller.dto;

import java.util.List;

public class BowlingGameResponse {

    private final String participantName;
    private final int nextFrameNumber;
    private final boolean isFinished;
    private final List<FrameInfo> frameInfos;

    public BowlingGameResponse(String participantName, int nextFrameNumber, boolean isFinished, List<FrameInfo> frameInfos) {
        this.participantName = participantName;
        this.nextFrameNumber = nextFrameNumber;
        this.isFinished = isFinished;
        this.frameInfos = frameInfos;
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getNextFrameNumber() {
        return nextFrameNumber;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public List<FrameInfo> getFrameInfos() {
        return frameInfos;
    }
}
