package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.dto.CountOfFallenPinsDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private static final int STOP_SCORE = -1;
    private static final int MAX_COUNT_OF_FRAME = 10;
    private static final int MIN_COUNT_OF_FRAME = 0;
    
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
    
    public List<Integer> getCumulativeScores() {
        List<Integer> cumulativeScores = new ArrayList<>();
        addCumulativeScores(cumulativeScores);
    
        return cumulativeScores;
    }
    
    private void addCumulativeScores(final List<Integer> cumulativeScores) {
        int cumulativeScore = 0;
        for (int countOfFrameOrder = MIN_COUNT_OF_FRAME; countOfFrameOrder < MAX_COUNT_OF_FRAME; countOfFrameOrder++) {
            cumulativeScore = calculateCumulativeScore(countOfFrameOrder, cumulativeScore);
            cumulativeScores.add(cumulativeScore);
        }
    }
    
    private int calculateCumulativeScore(final int countOfFrameOrder, final int cumulativeScore) {
        if (frames.size() <= countOfFrameOrder) {
            return STOP_SCORE;
        }
        return frames.get(countOfFrameOrder).calculateCumulativeScore(cumulativeScore);
    }
}
