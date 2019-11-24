package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.FrameNumber.LAST_FRAME;

public class Frames {
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
        for (int i = 0; i < LAST_FRAME; i++) {
            Frame frame = new Frame(i + 1);
            frames.add(frame);
        }
    }

    public Frame frameByIndex(int index) {
        return frames.get(index);
    }

    public boolean isFallDownAble(int i) {
        return frames.get(i).isFallDownAble();
    }

    public void fallDown(int index, int pinCount) {
        frames.get(index).fallDown(pinCount);
    }

    public Score getScore(int index) {
        if (index < 0) {
            return Score.ofDefaultScore();
        }
        Score previousScore = getScore(index - 1);
        Frame frame = frames.get(index);
        Score score = frame.getScore(previousScore.getScore());

        return addBonusScore(score, index);
    }

    private Score addBonusScore(Score score, int index) {
        while (score.isLeft() && index < frames.size() - 1) {
            Frame nextFrame = frames.get(++index);
            score = nextFrame.addBonus(score);
        }
        return score;
    }
}
