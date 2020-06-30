package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final int FRAME_SIZE_CALCULATION = 1;
    private final List<Frame> frames;

    public Frames(Frame frame) {
        this.frames = new ArrayList<>();
        add(frame);
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

    public Frame getCurrentFrame() {
        return this.frames.get(frames.size() - FRAME_SIZE_CALCULATION);
    }

    public Frame getFrame(int frameNumber) {
        return this.frames.get(frameNumber - FRAME_SIZE_CALCULATION);
    }

    public int getCurrentFrameNumber() {
        return this.frames.indexOf(getCurrentFrame()) + FRAME_SIZE_CALCULATION;
    }

    public boolean isEndGame() {
        return getCurrentFrame().isEndGame();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();

        frames.forEach(frame -> sumScore(scores, frame));

        return scores;
    }

    private void sumScore(List<Integer> scores, Frame frame) {
        if (!frame.isCalculateScore()) {
            return;
        }

        int score = frame.getScore();

        score = scores.isEmpty() ? score : scores.get(scores.size() - 1) + score;
        scores.add(score);
    }
}
