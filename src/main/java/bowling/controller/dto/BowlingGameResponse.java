package bowling.controller.dto;

import java.util.List;

public class BowlingGameResponse {

    private final String participantName;
    private final int nextFrameNumber;
    private final boolean isFinished;
    private final List<String> pinDownResults;

    public BowlingGameResponse(String participantName, int nextFrameNumber, boolean isFinished, List<String> pinDownResults) {
        this.participantName = participantName;
        this.nextFrameNumber = nextFrameNumber;
        this.isFinished = isFinished;
        this.pinDownResults = pinDownResults;
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

    public List<String> getPinDownResults() {
        return pinDownResults;
    }
}
