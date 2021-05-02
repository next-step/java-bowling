package bowling.controller.dto;

import bowling.domain.dto.FrameBoard;

import java.util.List;

public class BowlingGameResponse {

    private final boolean isGameEnd;
    private final String nextTurnParticipant;
    private final List<FrameBoard> frameBoards;

    public BowlingGameResponse(boolean isGameEnd, String nextTurnParticipant, List<FrameBoard> frameBoards) {
        this.isGameEnd = isGameEnd;
        this.nextTurnParticipant = nextTurnParticipant;
        this.frameBoards = frameBoards;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public String getNextTurnParticipant() {
        return nextTurnParticipant;
    }

    public List<FrameBoard> getFrameBoards() {
        return frameBoards;
    }
}
