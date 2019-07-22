package bowling.domain;

import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 13:35
 */
public class Frames {
    private List<Frame> frames;
    private Frame currentFrame;

    public Frames() {
        this.frames = new ArrayList<>();
        this.currentFrame = new NormalFrame();
    }

    public boolean bowl(int fallCount) {
        if (isGameOver()) {
            return false;
        }

        currentFrame = currentFrame.bowl(fallCount);
        if (!match(currentFrame)) {
            frames.add(currentFrame);
        }
        return true;
    }

    public List<String> displayState() {
        return frames.stream()
                .map(Frame::getState)
                .map(State::printState)
                .collect(Collectors.toList());
    }

    public int currentFrameNumber() {
        return currentFrame.getNumber();
    }

    private boolean isGameOver() {
        if (currentFrame instanceof FinalFrame && currentFrame.isGameOver()) {
            return true;
        }
        return false;
    }

    private boolean match(Frame sourceFrame) {
        return frames.stream()
                .filter(target -> target.equals(sourceFrame))
                .findFirst()
                .isPresent();
    }
}
