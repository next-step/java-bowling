package bowling.controller;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;
import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Participant;
import bowling.service.BowlingGameService;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameController {

    private final BowlingGameService service;

    public BowlingGameController() {
        service = new BowlingGameService();
    }

    public BowlingGameResponse startGame(BowlingGameRequest request) {
        Participant participant = new Participant(request.getParticipantName());
        service.startGame(participant);
        return assembleGameResponse(participant, service.findFrames(participant));
    }

    public BowlingGameResponse pitchBall(BowlingGameRequest request) {
        Participant participant = new Participant(request.getParticipantName());
        service.pitchBall(participant, request.getPitchCount());
        return assembleGameResponse(participant, service.findFrames(participant));
    }

    private BowlingGameResponse assembleGameResponse(Participant participant, Frames frames) {
        return new BowlingGameResponse(participant.getName(), frames.nextTurnNumber(), frames.isFinished(), assemblePinDownResults(frames));
    }

    private List<String> assemblePinDownResults(Frames frames) {
        List<String> pinDownResults = new ArrayList<>();
        for (Frame frame : frames) {
            pinDownResults.add(assemblePinDownResult(frame.getScoreBoards()));
        }
        return pinDownResults;
    }

    private String assemblePinDownResult(List<String> scoreBoards) {
        return String.join(Frames.SCORE_BOARD_DELIMITER, scoreBoards);
    }
}
