package bowling.controller;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;
import bowling.domain.dto.FrameInfo;
import bowling.domain.*;
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
        return new BowlingGameResponse(participant.getName(), frames.nextTurnNumber(), assembleFrameInfos(frames), frames.isFinished());
    }

    private List<FrameInfo> assembleFrameInfos(Frames frames) {
        List<FrameInfo> frameInfos = new ArrayList<>();
        for (Frame frame : frames) {
            FrameInfo frameInfo = frame.assembleFrameInfo();
            frameInfos.add(frameInfo);
        }
        return frameInfos;
    }

}
