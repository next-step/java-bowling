package bowling_refactor.domain;


import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        for (int i = 0; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            Frame frame = new Frame(i+1);
            frames.add(frame);
        }
    }

    public Frames(List<Frame> frames) {
        this.frames = new ArrayList<>(frames);
    }

    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public boolean isLeftPin(int index) {
        return getFrame(index).isLeftPin();
    }

    public void hit(int index, int pin) {
        getFrame(index).hit(pin);
    }

    public Score getScore(int index) {
        if (index < 0) {
            return Score.ofNoneScore();
        }

        Score previousScore = getScore(index - 1);
        Frame frame = frames.get(index);
        Score currentScore = frame.getScore(previousScore.getScore());
        return addBonusScore(currentScore, index);
    }

    private Score addBonusScore(Score score, int index) {
        int maxIndex = frames.size() - 1;
        while (score.isLeft() && index < maxIndex) {
            Frame nextFrame = frames.get(++index);
            score = nextFrame.addBonus(score);
        }
        return score;
    }

}
