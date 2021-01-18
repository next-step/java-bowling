package bowling.domain;

public interface Frame {

    int getFrameNo();

    /**
     * 볼링공을 던져서 넘어뜨린 볼링핀 수를 기입한다.
     * 최대 10개의 핀을 쓰러뜨릴 수 있다
     *
     * @param countOfFallDownPins
     */
    void mark(int countOfFallDownPins);

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
     * 다음 프레임을 가져온다
     * <p>
     * 볼링은 총 10개 프레임을 가진다
     *
     * @return
     */
    Frame createNext();

    Frame next();

    default boolean hasNext(){
        return next() != null;
    }

    FrameInfo toFrameInfo();

    /**
     * 현재 프레임의 스코어
     *
     * 스트라이크 - 다음공, 다다음공 으로 쓰러뜨린 핀수를 더한다
     * 스페어 - 다음공 으로 쓰러뜨린 핀수를 더한다
     *
     * @return
     */
    FrameScore getScore();

    FrameScore addScoreTo(FrameScore score);

    static Frame first() {
        return new NormalFrame(1);
    }

    static Frame createNormal(int frameNo) {
        return new NormalFrame(frameNo);
    }

    static Frame createFinal(int frameNo) {
        return new FinalFrame(frameNo);
    }
}
