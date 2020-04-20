package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.SPARE;
import static bowling.domain.frame.FrameResult.STRIKE;

public class Frames {
    private static final int FIRST_FRAME_ID = 1;
    private static final int MAX_POINT = 10;
    private static final int DOUBLE = 2;
    private static final int OFFSET = 1;

    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getFrameId(int index) {
        return frames.get(index).getFrameId();
    }


    public int getScoreOfFrame(int frameId){
        if(frameId == 1){
            return getScoreUpToFirstFrame();
        }

        if(frameId == 2){
            return getScoreUpToSecondFrame();
        }

        return getScoreUpToNormalFrame(frameId);
    }

    public int getScoreUpToFirstFrame(){
        Frame frame = frames.get(0);

        if(frame.isResult(STRIKE) || frame.isResult(SPARE)){
            return 0;
        }

        return getFrameScore(1);
    }

    public int getScoreUpToSecondFrame(){
        Frame frame = frames.get(1);
        Frame firstFrame = frames.get(0);

        if(frame.isResult(STRIKE) && firstFrame.isResult(STRIKE)){
            return 0;
        }

        if (frame.isGutterOrMiss()) {
            return getTotalPointUntil(2);
        }

        return getTotalPointUntil(1);
    }

    public int getScoreUpToNormalFrame(int frameId){
        Frame frame = frames.get(frameId - OFFSET);

        if(frame.isResult(STRIKE)){
            return getScoreWhenCurrentIsStrike(frameId);
        }

        if(frame.isResult(SPARE)){
            return getScoreWhenCurrentIsSpare(frameId);
        }

        return getTotalPointUntil(frameId);
    }

    public int getScoreWhenCurrentIsSpare(int currentFrameId){
        return getTotalPointUntil(currentFrameId - 1);
    }

    public int getScoreWhenCurrentIsStrike(int currentFrameId){
        Frame frame = frames.get(currentFrameId - OFFSET);

        if(Frames.of(frames).getPreviousFrame(frame).isResult(STRIKE)){
            return getTotalPointUntil(currentFrameId - 2);
        }

        if(Frames.of(frames).getPreviousFrame(frame).isResult(SPARE)){
            return getTotalPointUntil(currentFrameId - 1);
        }

        return getTotalPointUntil(currentFrameId);
    }

    public int getFrameScore(int frameId) {
        Frame frame = frames.get(frameId - OFFSET);

        if (frame.isFinalFrame()) {
            return frame.getPointSumOnlyThisFrame();
        }

        if (frame.isResult(STRIKE)) {
            return findFrameScoreWhenStrike(frame);
        }

        if (frame.isResult(SPARE)) {
            int firstPointOfNextFrame = getNextFrameByCurrentId(frameId).getFirstPoint();
            return (frame.getPointSumOnlyThisFrame() + firstPointOfNextFrame);
        }

        return frame.getPointSumOnlyThisFrame();
    }

    public int getTotalPointUntil(int currentFrameId) {
        return IntStream.rangeClosed(FIRST_FRAME_ID, currentFrameId)
                .map(it -> Frames.of(frames).getFrameScore(it))
                .sum();
    }

    public Frame getPreviousFrame(Frame currentFrame) {
        if (!currentFrame.isFirstFrame()) {
            return getPrevFrameByCurrentId(currentFrame.getFrameId());
        }

        return currentFrame;
    }

    private Frame getNextFrameByCurrentId(int currentFrameId) {
        return frames.get(currentFrameId);
    }

    private int findFrameScoreWhenStrike(Frame currentFrame) {
        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());

        if (nextFrame.isResult(STRIKE)) {
            return findFrameScoreWhenCurrentAndNextAreStrike(currentFrame);
        }

        return (MAX_POINT + nextFrame.getPointSumOnlyThisFrame());
    }

    private int findFrameScoreWhenCurrentAndNextAreStrike(Frame currentFrame) {
        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());

        if (currentFrame.isNineth()) {
            return (currentFrame.getFirstPoint() + nextFrame.getFirstPoint() + nextFrame.getThirdPoint());
        }

        int firstOfNextOfNext = getNextFrameByCurrentId(nextFrame.getFrameId()).getFirstPoint();
        return (MAX_POINT * DOUBLE) + firstOfNextOfNext;
    }

    private Frame getPrevFrameByCurrentId(int currentId) {
        return frames.get(currentId - (OFFSET * DOUBLE));
    }
}