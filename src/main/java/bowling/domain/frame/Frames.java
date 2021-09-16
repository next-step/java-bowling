package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;

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
        int framesIndex = size() - 1;
        if (framesIndex >= 0 && !isNext()) {
            nthFrameOf(framesIndex).addScore(score);
            return;
        }
        addFrame(score);
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
        return isNextNthFrameOf(frameNumberIndex);
    }

    public boolean isFinish() {
        return frames.size() == Frames.TOTAL_FRAME_NUMBER
            && isNextNthFrameOf(Frames.TOTAL_FRAME_NUMBER - 1);
    }

    public List<Frame> frames() {
        return frames;
    }

    public int size() {
        return frames.size();
    }

    public Frame lastFrame() {
        return frames.get(
            frames.size() - 1
        );
    }

    private boolean isNextNthFrameOf(int nth) {
        return frames.get(nth).isNextFrame();
    }

    public Frame nthFrameOf(int nth) {
        return frames.get(nth);
    }

    public List<Score> nthFrameScoresOf(int nth) {
        return nthFrameOf(nth).scores();
    }
}
