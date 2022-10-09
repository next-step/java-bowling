package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.dto.CountOfFallenPinsDTO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private final LinkedList<Frame> frames;
    
    public Frames() {
        this.frames = new LinkedList<>(List.of(new NormalFrame(1)));
    }
    
    public boolean bowl(final CountOfFallenPinsDTO countOfFallenPinsDTO) {
        Frame currentFrame = frames.getLast();
        Frame frameAfterBowl = currentFrame.bowl(countOfFallenPinsDTO.getCountOfFallenPins());
        
        return isCurrentFrameRunning(currentFrame, frameAfterBowl);
    }
    
    private boolean isCurrentFrameRunning(final Frame currentFrame, final Frame frameAfterBowl) {
        if (!currentFrame.equals(frameAfterBowl)) {
            addFrame(currentFrame, frameAfterBowl);
            return false;
        }
        return true;
    }
    
    private void addFrame(final Frame currentFrame, final Frame frameAfterBowl) {
        if (currentFrame.isNormalFrame()) {
            frames.add(frameAfterBowl);
        }
    }
    
    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
