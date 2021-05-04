package bowling.domain.dto;

import java.util.List;

public class FrameBoard {

    private final String participantName;
    private final int nextTurnNumber;
    private final List<FrameInfo> frameInfos;
    private final boolean isFinished;

    public FrameBoard(String participantName, int nextTurnNumber, List<FrameInfo> frameInfos, boolean isFinished) {
        this.participantName = participantName;
        this.nextTurnNumber = nextTurnNumber;
        this.frameInfos = frameInfos;
        this.isFinished = isFinished;
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getNextTurnNumber() {
        return nextTurnNumber;
    }

    public List<FrameInfo> getFrameInfos() {
        return frameInfos;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
