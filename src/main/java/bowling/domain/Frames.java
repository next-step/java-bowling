package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int LAST_ROUND = 10;

    private final List<FrameStrategy> frames;
    private final Scores scores;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame());

        this.scores = new Scores();
    }

    public List<FrameStrategy> getFrames() {
        return frames;
    }

    public FrameStrategy frame(int index) {
        return frames.get(index);
    }

    public int getScore(FrameStrategy frame) {
        return scores.score(frame);
    }

    public int frameSize() {
        return frames.size();
    }

    public void proceedRound(int frameNumber, PinNumber pinNumber) {
        frames.get(frameNumber - 1).play(pinNumber);
        if (!hasRemainTurn(frameNumber) && frameNumber < LAST_ROUND) {
            frames.add(frames.get(frameNumber - 1).newNextFrame(frameNumber));
        }

        calculateScore(frameNumber);
    }

    public boolean hasRemainTurn(int frameNumber) {
        return frames.get(frameNumber - 1).hasNext();
    }

    public void calculateScore(int frameNumber) {
        scores.record(frames.get(0), null);
        for (int i = 1; i < frameNumber; i++) {
            scores.record(frames.get(i), frames.get(i -1));
        }
    }
}