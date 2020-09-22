package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.ui.result.DisplayRolledResult;

import static java.util.stream.Collectors.toList;

public final class Frames {
    public static final int MAX_FRAMES_SIZE = 10;
    public static final int MAX_FOUNDATION_FRAME_SIZE = 9;
    private final List<Frame> frames;
    private int zeroBaseCurrentFrameIndex;

    Frames() {
        frames = createFrames();
        zeroBaseCurrentFrameIndex = 0;
    }

    private List<Frame> createFrames() {
        final List<Frame> temp = Stream.generate(FoundationFrame::new)
                                 .limit(MAX_FOUNDATION_FRAME_SIZE)
                                 .collect(toList());
        temp.add(new TerminateFrame());
        for(int index = 0; MAX_FRAMES_SIZE > index; ++index){
            final Frame prevFrame = (0 == index) ? null : temp.get(index - 1);
            final Frame nextFrame = (MAX_FOUNDATION_FRAME_SIZE < index + 1) ? null : temp.get(index + 1);
            temp.get(index)
                .score(prevFrame, nextFrame);
        }
        return Collections.unmodifiableList(temp);
    }

    public static Frames of() {
        return new Frames();
    }

    public void saveRolledResultAndShouldNextFrame(int fallenPins){
        updateCurrentFrameByRolledResult(fallenPins);
        shouldNextFrame();
    }

    private void updateCurrentFrameByRolledResult(int fallenPins) {
        if (isNotCompleteFrames()) {
            currentFrame().updateRolledResult(fallenPins);
        }
    }

    private void shouldNextFrame(){
        if (isNotCompleteFrames()) {
            zeroBaseCurrentFrameIndex += currentFrame().increaseNextStepFrame();
        }
    }

    boolean isCompleteFrames(){
        return MAX_FRAMES_SIZE <= currentFrameIndex();
    }

    public boolean isNotCompleteFrames(){
        return !isCompleteFrames();
    }

    public int currentFrameIndex(){
        return zeroBaseCurrentFrameIndex;
    }

    public int getTotalScore(){
        int cursor = MAX_FOUNDATION_FRAME_SIZE > currentFrameIndex() ?
            currentFrameIndex() - 1 : MAX_FOUNDATION_FRAME_SIZE;

        return frames.get(cursor)
                     .getScore();
    }

    public List<DisplayRolledResult> toRolledResults() {
        return frames.stream()
                     .map(Frame::toDisplayRolledResult)
                     .collect(toList());
    }

    private Frame currentFrame(){
        return frames.get(currentFrameIndex());
    }

    public boolean hasNextFrameIndex(int currentFrameIndex) {
        return currentFrameIndex() <= currentFrameIndex;
    }
}
