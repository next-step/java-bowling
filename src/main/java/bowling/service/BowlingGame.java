package bowling.service;

import bowling.domain.Frame;
import bowling.domain.Frames;

/**
 * 볼링 게임은 10개 프레임, 사용자, 프레임 구분할 수 있는 번호를 갖는다.
 */
public class BowlingGame {

    private final Frames frames;
    private final String user;
    private int frameNumber;

    public BowlingGame(String user) {
        this.frames = new Frames(Frame.init());
        this.user = user;
        this.frameNumber = 0;
    }

    public void bowl(int pins) {
        frames.bowl(pins);

        if (frames.isNextFrame()) { // 2 시도로 다음 프레임확인
            frameNumber++;
        }
    }

    public boolean isLastGame() {
        return frameNumber == 10;
    }

    public int tryingCount() {
        return frames.tryCount();
    }

    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", user, frameNumber, frames);
    }
}
