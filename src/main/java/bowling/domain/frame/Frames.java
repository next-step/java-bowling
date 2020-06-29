package bowling.domain.frame;

import bowling.domain.dto.ScoreSignaturesDto;
import bowling.domain.score.FrameScores;
import bowling.domain.score.PitchScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int INDEX_CONSTANT = 1;
    private static final int TOTAL_FRAME_COUNTS = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initiate());
        return new Frames(frames);
    }

    public void bowl(PitchScore pitchScore) {
        getCurrentFrame().bowl(pitchScore);
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - INDEX_CONSTANT);
    }

    public void moveToNextFrame() {
        Frame currentFrame = getCurrentFrame();
        int currentFrameIndex = frames.size();
        if (currentFrameIndex != TOTAL_FRAME_COUNTS && currentFrame.isFinished()) {
            Frame nextFrame = currentFrame.next(currentFrameIndex);
            frames.add(nextFrame);
        }
    }

    public boolean hasNextTurn() {
        return !(frames.size() == TOTAL_FRAME_COUNTS && getCurrentFrame().isFinished());
    }

    public FrameScores getFrameScores() {
        return FrameScores.of(getFrames());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isCurrentFrameFinished() {
        return getCurrentFrame().isFinished();
    }
    
    public List<ScoreSignaturesDto> getScoreSignatureDtos() {
        return frames.stream()
                .map(Frame::getPitchScoreSignaturesDto)
                .collect(Collectors.toList());
    }
}
