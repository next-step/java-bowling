package bowling.domain;

import bowling.domain.dto.FrameBoard;

import java.util.*;
import java.util.stream.Collectors;

public class BowlingGame {

    private static final String NON_PARTICIPANT = "";

    private final Map<Participant, Frames> game;

    public BowlingGame(Map<Participant, Frames> game) {
        this.game = game;
    }

    public boolean isEnd() {
        boolean isEnd = true;
        for (Frames frames : game.values()) {
            isEnd = isEnd && frames.isFinished();
        }
        return isEnd;
    }

    public String nextTurnParticipantName() {
        Optional<FrameBoard> unPitchedFrame = unPitchedFrame();
        if (unPitchedFrame.isPresent()) {
            return unPitchedFrame.get().getParticipantName();
        }
        return unFinishedFrames().stream()
                .map(FrameBoard::getParticipantName)
                .findFirst()
                .orElse(NON_PARTICIPANT);
    }

    public List<FrameBoard> frameBoards() {
        List<Participant> participants = new ArrayList<>(game.keySet());
        return participants.stream()
                .map(participant -> {
                    Frames frames = game.get(participant);
                    return frames.assembleFrameBoard(participant);
                })
                .collect(Collectors.toList());
    }

    private List<FrameBoard> unFinishedFrames() {
        return frameBoards().stream()
                .filter(frameBoard -> !frameBoard.isFinished())
                .collect(Collectors.toList());
    }

    private int lastTurnNumber() {
        List<FrameBoard> frameBoards = frameBoards();
        OptionalInt max = frameBoards.stream().mapToInt(FrameBoard::getNextTurnNumber).max();
        return max.orElseThrow(() -> new IllegalStateException("프레임 실행정보가 존재하지 않습니다."));
    }

    private Optional<FrameBoard> unPitchedFrame() {
        int lastTurnNumber = lastTurnNumber();
        return unFinishedFrames().stream()
                .filter(frameBoard -> frameBoard.getNextTurnNumber() < lastTurnNumber)
                .findFirst();
    }

}
