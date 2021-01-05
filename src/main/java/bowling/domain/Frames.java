package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

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

        int previousScore = previousFrameScoreSumUp(currentFrame);

        currentFrame.setScore(previousScore);

        if (frames.size() >= MIN_FRAME_COUNT_TO_SCORE) {
            renewScore(currentFrame, knockedDownPins);
        }

    }

    private int previousFrameScoreSumUp(Frame currentFrame) {
        return frames.stream()
                .filter(frame -> frame.getIndex() == (currentFrame.getIndex() - 1))
                .mapToInt(Frame::sumUpCurrentScore)
                .sum();
    }

    private void renewScore(Frame currentFrame, int knockedDownPins){
        if (hasUnfinishedScore()) {
            frames.stream()
                    .filter(frame -> frame.isRenewScore(currentFrame))
                    .forEach(frame -> renewFrameScore(frame, knockedDownPins));
        }
    }

    private void renewFrameScore(Frame frame, int knockedDownPins){
        frame.renewScore(knockedDownPins);
        renewNextFrame(frame.getIndex());
    }

    private void renewNextFrame(int nextFrameIndex) {
        Frame nextFrame = frames.get(nextFrameIndex);
        nextFrame.setScore(previousFrameScoreSumUp(nextFrame));
    }

    public boolean hasUnfinishedScore() {
        return frames.stream()
                .anyMatch(frame -> frame.countLeftBonus() > 0);
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

    public int count() {
        return frames.size();
    }

    public Frame searchFrame(int index) {
        return frames.get(index);
    }

    public PitchResults searchFramePitchResult(int index) {
        return frames.get(index).getPitchResults();
    }
}

