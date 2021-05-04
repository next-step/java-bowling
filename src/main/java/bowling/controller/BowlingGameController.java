package bowling.controller;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;
import bowling.domain.BowlingGame;
import bowling.domain.Participant;
import bowling.service.BowlingGameService;

public class BowlingGameController {

    private final BowlingGameService service;

    public BowlingGameController() {
        service = new BowlingGameService();
    }

    public BowlingGameResponse startGame(BowlingGameRequest request) {
        Participant participant = new Participant(request.getParticipantName());
        service.startGame(participant);
        return assembleGameResponse(service.findBowlingGame());
    }

    public BowlingGameResponse pitchBall(BowlingGameRequest request) {
        Participant participant = new Participant(request.getParticipantName());
        service.pitchBall(participant, request.getPitchCount());
        return assembleGameResponse(service.findBowlingGame());
    }

    private BowlingGameResponse assembleGameResponse(BowlingGame bowlingGame) {
        return new BowlingGameResponse(bowlingGame.isEnd(), bowlingGame.nextTurnParticipantName(), bowlingGame.frameBoards());
    }

}
