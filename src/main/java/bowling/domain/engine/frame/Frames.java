package bowling.domain.engine.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.state.CannotCalculateScoreException;
import bowling.dto.FrameStateDto;
import bowling.dto.FramesDto;
import bowling.dto.ScoresDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int TOTAL_FRAMES = 10;

    private final LinkedList<Frame> frames;

    private Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        LinkedList<Frame> frames = FrameCreator.createFrames();
        return new Frames(frames);
    }

    public void roll(RollResult rollResult) {
        nextFrame().roll(rollResult);
    }

    private Frame nextFrame() {
        return frames.stream()
                     .filter(frame -> !frame.isEnded())
                     .findFirst()
                     .orElseThrow(IllegalStateException::new);
    }

    public int getNextFrameNumber() {
        return frames.indexOf(nextFrame()) + 1;
    }

    public boolean isAllFrameEnded() {
        return frames.size() == TOTAL_FRAMES && frames.getLast().isEnded();
    }

    public boolean isFinishedFrame(FrameNumber frameNumber) {
        Frame frame = frames.get(frameNumber.getNumber() - 1);
        return frame.isEnded();
    }

    public FramesDto export() {
        return FramesDto.of(this);
    }

    public List<FrameStateDto> exportFrameStates() {
        return frames.stream()
                     .map(this::exportFrame)
                     .collect(toList());
    }

    private FrameStateDto exportFrame(Frame frame) {
        return FrameStateDto.of(frame);
    }

    public ScoresDto exportScores() {
        return frames.stream()
                     .map(this::getScoreOf)
                     .collect(collectingAndThen(toList(), ScoresDto::of));
    }

    private Score getScoreOf(Frame frame) {
        try {
            return frame.getScore();
        } catch (CannotCalculateScoreException e) {
            return UnavailableScore.init();
        }
    }

}
