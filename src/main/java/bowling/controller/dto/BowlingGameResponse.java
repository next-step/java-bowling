package bowling.controller.dto;

import bowling.domain.dto.FrameInfo;

import java.util.List;

public class BowlingGameResponse {

    private final String participantName;
    private final int nextFrameNumber;
    private final List<FrameInfo> frameInfos;
    private final boolean isFinished;

    public BowlingGameResponse(String participantName, int nextFrameNumber, List<FrameInfo> frameInfos, boolean isFinished) {
        this.participantName = participantName;
        this.nextFrameNumber = nextFrameNumber;
        this.frameInfos = frameInfos;
        this.isFinished = isFinished;
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getNextFrameNumber() {
        return nextFrameNumber;
    }

    public List<FrameInfo> getFrameInfos() {
        return frameInfos;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
