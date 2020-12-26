package bowling.domain;

import java.util.*;


public class Frames {

    private static final int MIN_FRAME_COUNT_TO_SCORE = 2;
    private static final int FRAME_START_INDEX = 1;
    public static final int MAX_FRAME_COUNT = 10;

    private List<Frame> frames;

    private Frames(){
        this.frames = new ArrayList<>();
    }

    public static Frames of() {
        return new Frames();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME_COUNT && this.getLast().isEnd();
    }

    public void execute(int knockedDownPins) {
        Frame currentFrame = this.getLast();
        currentFrame.start(knockedDownPins);
        currentFrame.setScore(knockedDownPins);

        if (frames.size() >= MIN_FRAME_COUNT_TO_SCORE) {
            renewScore(currentFrame, knockedDownPins);
        }

    }

    private void renewScore(Frame currentFrame, int knockedDownPins){
        if (hasUnfinishedScore()) {
            frames.stream()
                    .filter(frame -> frame.getScore().getLeftBonusCount() > 0 && !(frame.getIndex() == currentFrame.getIndex()))
                    .forEach(frame -> frame.renewScore(knockedDownPins));
        }
    }

    public boolean hasUnfinishedScore() {
        return frames.stream()
                .anyMatch(frame -> frame.getScore().getLeftBonusCount() > 0);
    }

    private int getCurrentFrameIndex() {
        return frames.size();
    }

    private void makeFirstFrame() {
        frames.add(NormalFrame.from(FRAME_START_INDEX));
    }

    public void makeNextFrames() {
        if ((this.getLast().isEnd()) && (frames.size() < MAX_FRAME_COUNT)) {
            Frame nextFrame = this.getLast().makeNextFrame(getCurrentFrameIndex());
            frames.add(nextFrame);
        }
    }

    public Frame getLast(){
        if (frames.isEmpty()) {
            makeFirstFrame();
        }

        return frames.stream()
                .reduce((first, second) -> second)
                .orElseThrow(NoSuchElementException::new);
    }

}

