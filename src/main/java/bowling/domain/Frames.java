package bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int FRAME_NUMBER = 10;
    private static final int ONE = 1;

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

        updateBonus(clearPinCount);

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
        return this.frames.get(this.frames.size() - ONE);
    }

    private Frame getFrame(int index) {
        return frames.get(index);
    }

    private void calculateScore() {
        if (frames.size() - ONE <= scoreCalculateIndex) {
            return;
        }

        Frame frame = getFrame(scoreCalculateIndex);
        if (frame.endCalculate()) {
            Frame nextFrame = getFrame(scoreCalculateIndex + ONE);
            nextFrame.addScore(frame.getTotalScore());

            scoreCalculateIndex++;
        }
    }

    private void updateBonus(int clearPinCount) {
        frames.stream()
                .filter(Frame::availableBonus)
                .forEach(bonusAvailableFrame -> bonusAvailableFrame.updateBonus(clearPinCount));
    }
}
