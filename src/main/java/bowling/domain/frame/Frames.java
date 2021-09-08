package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.score.Score;

public class Frames {
    public static final int TOTAL_FRAME_NUMBER = 10;

    private final List<Frame> frames;

    private Frames(List<Integer> scores) {
        frames = new ArrayList<>();
        throwBalls(scores);
    }

    public static Frames from(List<Integer> scores) {
        return new Frames(scores);
    }

    public void throwBalls(int score) {
        throwBall(Score.from(score));
    }

    public void throwBalls(List<Integer> scores) {
        scores.forEach(i -> throwBall(Score.from(i)));
    }

    private void throwBall(Score score) {
        int framesIndex = frames.size() - 1;
        if (framesIndex >= 0 && !isNext()) {
            frames.get(framesIndex).addScore(score);
            return;
        }
        addFrame(score);                       // first 프레임내 첫 투구
    }

    private void addFrame(Score score) {
        int framesSize = frames.size();
        if (framesSize < TOTAL_FRAME_NUMBER - 1) {
            frames.add(NormalFrame.of(score));
        }

        if (framesSize == TOTAL_FRAME_NUMBER - 1) {
            frames.add(FinalFrame.of(score));
        }
    }

    public boolean isNext() {
        if (frames.size() == 0) {
            return false;
        }

        int frameNumberIndex = frames.size() - 1;
        return frames.get(frameNumberIndex).isNextFrame();
    }

    public boolean isFinish() {
        return frames.size() == Frames.TOTAL_FRAME_NUMBER
            && frames.get(Frames.TOTAL_FRAME_NUMBER - 1).isNextFrame();
    }

    public int frameNumber() {
        if (frames.size() <= 0) {
            return 1;
        }

        if (isNext()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    public List<Frame> frames() {
        return frames;
    }

    public Frame lastFrame() {
        return frames.get(
            frames.size() - 1
        );
    }
}
