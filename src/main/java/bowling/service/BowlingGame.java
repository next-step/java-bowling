package bowling.service;

import bowling.domain.Frame;

import java.util.Arrays;
import java.util.List;

/**
 * 볼링 게임은 10개 프레임, 사용자, 프레임 구분할 수 있는 번호를 갖는다.
 */
public class BowlingGame {

    private final String user;
    private final List<Frame> frame;
    private int frameNumber;

    // 볼링 게임을 진행하기 위해서 플레이어 이름을 전달 받아 게임에 대한 기본 값을을 설정한다.
    public BowlingGame(String user) {
        this.frame = Arrays.asList(new Frame(0, 0));
        this.frameNumber = 0;
        this.user = user;
    }

    // 투구
    public void bowl(Integer pins) {
        frame.get(frameNumber).bowl(pins);
    }

    public boolean isLast() {
        return frameNumber == 10;
    }

    public int tryingCount() {
        return frame.get(frameNumber).tryCount();
    }

    public int frameNumber() {
        if (frame.get(frameNumber).nextFrame()) {
            frameNumber++;
        }
        return frameNumber;
    }
}
