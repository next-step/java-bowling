package bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int FRAME_NUMBER = 10;

    @Getter
    private int scoreCalculateIndex;
    @Getter
    private List<Frame> frames = new ArrayList<>();

    public void play(int clearPinCount) {
        if (frames.isEmpty()) {
            Frame frame = Frame.fistFrame();
            frame.roundPlay(clearPinCount);
            frames.add(frame);
            return;
        }

        frames.stream()
                .filter(Frame::availableBonus)
                .forEach(bonusAvailableFrame -> bonusAvailableFrame.updateBonus(clearPinCount));

        Frame frame = lastFrame();
        if (frame.isEndFrame()) {
            frame = frame.next();

            frames.add(frame);
        }

        frame.roundPlay(clearPinCount);

        calculateScore();
    }

    public boolean isEnd() {
        if (frames.size() >= FRAME_NUMBER) {
            return lastFrame().isEndFrame();
        }

        return false;
    }

    public int getFrameNumber() {
        return frames.size();
    }

    private Frame lastFrame() {
        return this.frames.get(this.frames.size() - 1);
    }

    private Frame getFrame(int index) {
        return frames.get(index);
    }

    private void calculateScore() {
        for (int i = scoreCalculateIndex; i < frames.size() - 1; i++) {
            Frame frame = getFrame(i);
            if (frame.endCalculate()) {
                Frame nextFrame = getFrame(i + 1);
                nextFrame.addScore(frame.getTotalScore());

                scoreCalculateIndex++;

                continue;
            }
        }
    }
}
