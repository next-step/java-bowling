package bowling.domain;

import java.util.List;

public interface Frame {

    int getFrameNo();

    /**
     * 볼링공을 던져서 넘어뜨린 볼링핀 수를 기입한다.
     * 최대 10개의 핀을 쓰러뜨릴 수 있다
     *
     * @param countOfFallDown
     */
    void mark(int countOfFallDown);

    /**
     * 현재 프레임 종료여부
     * <p>
     * 일반 프레임은 2번의 shot 기회가 있고 마지막 프레임은 3번의 shot 기회가 있다.
     * 일반 프레임은 shot 기회를 다사용하기 전에 모든 핀을 쓰러뜨리면 남은 기회에 상관없이 종료된다.
     * 마지막 프레임은 2번의 기회에 10개 핀을 쓰러뜨리면 1번의 shot 기회가 추가로 주어진다.
     *
     * @return
     */
    boolean isEnd();

    /**
     * 마지막 프레임 여부
     *
     * @return
     */
    boolean isLast();

    /**
     * 다음 프레임을 생성한다
     * <p>
     * 볼링은 총 10개 프레임을 가진다
     *
     * @return
     */
    Frame nextFrame();

    /**
     * 쓰러뜨린 핀 마크를 가져온다
     *
     * @return 불변컬랙션이다
     */
    List<PinMark> getPinMarks();

    /**
     * 현재 프레임까지 스코어를 가져온다
     *
     * @return
     */
    int getScore();

    static Frame first() {
        return new GeneralFrame(1, null);
    }

    static Frame general(int frameNo, Frame prevFrame) {
        return new GeneralFrame(frameNo, prevFrame);
    }

    static Frame last(int frameNo, Frame prevFrame) {
        return new LastFrame(frameNo, prevFrame);
    }
}
