package bowling.controller;

import bowling.controller.dto.FrameStatus;
import bowling.controller.dto.GameStatus;
import bowling.domain.Frames;
import bowling.domain.Game;
import bowling.domain.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameAssembler {
    private static final int ZERO = 0;

    public static GameStatus writeDto(Game game) {
        List<FrameStatus> frameStatuses = getFrameStatuses(game.getFrames());
        return new GameStatus(frameStatuses, game.getPlayerName());
    }

    private static List<FrameStatus> getFrameStatuses(Frames frames) {
        List<FrameStatus> frameStatuses = new ArrayList<>();

        frameStatuses.addAll(getNotEmptyFrameStatuses(frames));
        frameStatuses.addAll(getEmptyFrameStatuses(frames.size()));

        return frameStatuses;
    }

    private static List<FrameStatus> getNotEmptyFrameStatuses(Frames frames) {
        List<FrameStatus> frameStatuses = new ArrayList<>();

        int beforeScore = ZERO;
        for (int i = 0, size = frames.size(); i < size; i++) {
            FrameStatus frameStatus = getFrameStatus(frames, i, beforeScore);
            frameStatuses.add(frameStatus);
            beforeScore = frameStatus.getScore().orElse(ZERO);
        }

        return frameStatuses;
    }

    private static FrameStatus getFrameStatus(Frames frames, int index,
                                              int beforeScore) {
        Optional<Integer> optionalScore = frames.getFrameScore(index);

        if (optionalScore.isPresent()) {
            List<Pitch> pitches = frames.getFramePinCounts(index);
            return new FrameStatus(pitches, optionalScore.get() + beforeScore);
        }

        List<Pitch> pitches = frames.getFramePinCounts(index);
        return new FrameStatus(pitches, null);
    }

    private static List<FrameStatus> getEmptyFrameStatuses(int startIndex) {
        List<FrameStatus> frameStatuses = new ArrayList<>();
        for (int i = startIndex; i < Frames.DEFAULT_FRAME_SIZE; i++) {
            frameStatuses.add(FrameStatus.EMPTY);
        }

        return frameStatuses;
    }

    private GameAssembler() {
    }
}
