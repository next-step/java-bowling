package bowling.domain.dto;

import bowling.domain.Frames;
import bowling.domain.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameStatus {
    private static final int ZERO = 0;
    List<FrameStatus> frameStatuses;
    String playerName;

    public GameStatus(Frames frames, String playerName) {
        this.playerName = playerName;
        frameStatuses = new ArrayList<>();
        addFrameStatuses(frames);
    }

    private void addFrameStatuses(Frames frames) {
        int size = frames.size();
        int beforeScore = ZERO;
        for (int i = 0; i < size; i++) {
            beforeScore = addFrame(frames, i, beforeScore);
        }

        addEmptyFrameStatuses(size);
    }

    private int addFrame(Frames frames, int index, int beforeScore) {
        Optional<Integer> optionalScore = frames.getFrameScore(index);

        if (optionalScore.isPresent()) {
            int score = optionalScore.get() + beforeScore;
            List<Pitch> pitches = frames.getFramePinCounts(index);
            frameStatuses.add(new FrameStatus(pitches, score));
            return score;
        }

        List<Pitch> pitches = frames.getFramePinCounts(index);
        frameStatuses.add(new FrameStatus(pitches, null));
        return ZERO;
    }

    private void addEmptyFrameStatuses(int startIndex) {
        for (int i = startIndex; i < Frames.DEFAULT_FRAME_SIZE; i++) {
            frameStatuses.add(FrameStatus.EMPTY);
        }
    }

    public FrameStatus getFrameStatus(int i) {
        return frameStatuses.get(i);
    }

    public int getFrameStatusesSize() {
        return frameStatuses.size();
    }

    public String getPlayerName() {
        return playerName;
    }
}
