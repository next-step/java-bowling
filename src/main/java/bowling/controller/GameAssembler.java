package bowling.controller;

import bowling.controller.dto.FrameStatus;
import bowling.controller.dto.GameStatus;
import bowling.controller.dto.PlayerFrameStatus;
import bowling.domain.Frames;
import bowling.domain.Game;
import bowling.domain.PlayerFrames;
import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameAssembler {
    private static final int ZERO = 0;

    public static GameStatus writeDto(Game game) {
        return new GameStatus(getPlayerFrameStatus(game.getPlayerFrames()));
    }

    private static List<PlayerFrameStatus> getPlayerFrameStatus(
            PlayerFrames playerFrames) {
        return playerFrames.stream()
                .map(playerFrame -> new PlayerFrameStatus(
                        getFrameStatuses(playerFrame.getFrames()),
                        playerFrame.getPlayerName()))
                .collect(Collectors.toList());
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
        Score score = frames.getFrameScore(index);
        if (score.isCompleted()) {
            List<Pitch> pitches = frames.getFramePinCounts(index);
            return new FrameStatus(pitches, score.getScore() + beforeScore);
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
