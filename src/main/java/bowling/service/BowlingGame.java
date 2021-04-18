package bowling.service;

import bowling.domain.Frame;
import bowling.exception.PinOutOfSizeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 볼링 게임은 10개 프레임, 사용자, 프레임 구분할 수 있는 번호를 갖는다.
 */
public class BowlingGame {

    private final String user;
    private final List<Frame> frame;
    private int frameNumber;
    private Integer pins;
    private int tryCount;

    // 볼링 게임을 진행하기 위해서 플레이어 이름을 전달 받아 게임에 대한 기본 값을을 설정한다.

    public BowlingGame(String user) {
        this.frame = new ArrayList<>();
        this.frameNumber = 0;
        this.pins = 0;
        this.user = user;
    }
    // 핀을 넘어뜨렸을 때, 핀에 대한 값을 합산 ?

    public void bowl(Integer pins) {
        this.pins += pins;
        if(this.pins > 10) {
            throw new PinOutOfSizeException("10개 이상의 핀을 쓰러뜨릴 수 없습니다.");
        }
        this.tryCount++;
    }

    public boolean isLast() {
        return frameNumber == 10;
    }

    public int tryingCount() {
        return this.tryCount;
    }

    public int frameNumber() {
        return frameNumber;
    }
}
