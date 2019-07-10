package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 17:15
 */
public class GameBoard {
    private static final int LAST_INDEX = 1;
    private List<Frame> frames;

    GameBoard() {
        this.frames = new ArrayList<>();
    }

    int playCount() {
        return frames.size();
    }

    Map<Integer, FrameScore> play(int fallCount) {
        if (isEmpty()) {
            return nextFrame(fallCount);
        }
        return nowFrame(fallCount);
    }

    private Map<Integer, FrameScore> nowFrame(int fallCount) {
        boolean bowlSuccess = frames.get(getLastIndex()).bowl(fallCount);
        if (!bowlSuccess) {
            nextFrame(fallCount);
        }
        return frames.stream()
                .collect(Collectors.toMap(Frame::frameNumber, Frame::nowFrameScores));
    }

    private Map<Integer, FrameScore> nextFrame(int fallCount) {
        frames.add(Frame.of(fallCount));
        return frames.stream()
                .collect(Collectors.toMap(Frame::frameNumber, Frame::nowFrameScores));
    }

    private boolean isEmpty() {
        return frames.isEmpty();
    }

    private int getLastIndex() {
        return frames.size() - LAST_INDEX;
    }
}
