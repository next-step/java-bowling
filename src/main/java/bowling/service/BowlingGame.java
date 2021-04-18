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

    // 볼링 게임을 진행하기 위해서 플레이어 이름을 전달 받아 게임에 대한 기본 값을을 설정한다.
    public BowlingGame(String user) {
        this.frames = new Frames(new Frame(0, 0));
        this.user = user;
        this.frameNumber = 0;
    }

    // 투구
    public void bowl(int pins) {
        frames.bowl(pins);

        // 투구 후에 시도 횟수 확인 후에 다음 프레임으로
        if (frames.isNextFrame()) {
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
        return "BowlingGame{" +
                "frames=" + frames +
                ", user='" + user + '\'' +
                ", frameNumber=" + frameNumber +
                '}';
    }
}
