package bowling.controller;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;
import bowling.controller.dto.FrameInfo;
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
        return new BowlingGameResponse(participant.getName(), frames.nextTurnNumber(), assembleFrameInfos(frames));
    }

    private List<FrameInfo> assembleFrameInfos(Frames frames) {
        List<FrameInfo> frameInfos = new ArrayList<>();
        for (Frame frame : frames) {
            frameInfos.add(assembleFrameInfo(frame));
        }
        return frameInfos;
    }

    private FrameInfo assembleFrameInfo(Frame frame) {
        return new FrameInfo(assemblePinDownResults(frame.pitches()), frame.score(), frame.isFinalFrame(), frame.isSpare(), frame.isScoreDecided());
    }

    private List<Integer> assemblePinDownResults(Pitches pitches) {
        List<Integer> pinDownResults = new ArrayList<>();
        for (Pitch pitch : pitches) {
            pinDownResults.add(pitch.value());
        }
        return pinDownResults;
    }
}
