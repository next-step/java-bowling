package bowlingRefactor.domain;

import bowling.domain.frame.FrameRound;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        for (int i = 0; i < FrameRound.MAX_ROUND; i++) {
            Frame frame = new Frame(i+1);
            frames.add(frame);
        }
    }

    public Frames(List<Frame> frames) {
        this.frames = new ArrayList<>(frames);
    }

    public Frame get(int index) {
        return frames.get(index);
    }

    public boolean isLeftPin(int index) {
        return get(index).isLeftPin();
    }


    public void hit(int index, int pin) {
        get(index).hit(pin);
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
