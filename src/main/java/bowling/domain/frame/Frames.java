package bowling.domain.frame;

import bowling.domain.state.PinCount;
import bowling.domain.state.StateHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.Constants.FIRST_FRAME_NUMBER;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.create(new FrameNumber(FIRST_FRAME_NUMBER)));
    }

    public void play(PinCount felledPin) {
        Frame frame = getCurrentFrame();
        frame.play(felledPin);

        if (!frame.isLastFrame() && frame.isEndedFrame()) {
            Frame nextFrame = frame.getNext();
            addFrame(nextFrame);
        }
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private void addFrame(Frame frame) {
        if (Objects.nonNull(frame)) {
            frames.add(frame);
        }
    }

    public boolean isEnd() {
        return getCurrentFrame().isLastFrame();
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();

        for(Frame frame : frames) {
            addScore(scores, frame);
        }

        return scores;
    }

    private void addScore(List<Integer> scores, Frame frame) {
        if(!frame.canCalculateScore()) {
            //TODO: Exception 처리
        }

        int score = frame.getScore();

        if(scores.isEmpty()) {
            scores.add(score);
            return ;
        }

        int nextScore = scores.get(scores.size() - 1) + score;
        scores.add(nextScore);
    }

    public List<Frame> getValue() {
        return new ArrayList<>(frames);
    }
}
