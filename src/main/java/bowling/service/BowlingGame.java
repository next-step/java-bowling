package bowling.service;

import bowling.domain.Frame;
import bowling.domain.Frames;

import java.util.List;

/**
 * 볼링 게임은 10개 프레임, 사용자, 프레임 구분할 수 있는 번호를 갖는다.
 */
public class BowlingGame {

    public static final int BOWLING_LAST_FRAME = 10;
    public final Frames frames;
    private final String user;

    public BowlingGame(String user) {
        this.frames = new Frames(Frame.init());
        this.user = user;
    }

    public void bowl(int pins) {
        frames.bowl(pins);

        if (frames.isNextFrame()) {
            frames.nextFrame();
        }
    }

    public String user() {
        return user;
    }

    public List<Frame> frames() {
        return frames.list();
    }

    public boolean isLast() {
        return frames.isLastFrame();
    }

    public int currentFrameNumber() {
        return frames.frameNumber();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", user, frames);
    }
}
