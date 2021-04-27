package bowling.controller.dto;

public class BowlingGameRequest {

    private final String participantName;
    private final int pitchCount;

    public BowlingGameRequest(String participantName) {
        this(participantName, 0);
    }

    public BowlingGameRequest(String participantName, int pitchCount) {
        this.participantName = participantName;
        this.pitchCount = pitchCount;
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getPitchCount() {
        return pitchCount;
    }
}
