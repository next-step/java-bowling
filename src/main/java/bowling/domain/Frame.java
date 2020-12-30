package bowling.domain;

public interface Frame {

    enum Status{
        Strike, Spare, Miss, Gutter;
    }

    int getFrameNo();

    /**
     * 볼링공을 던져서 넘어뜨린 볼링핀 수를 기입한다.
     * 최대 10개의 핀을 쓰러뜨릴 수 있다
     *
     * @param countOfFallDown
     */
    void mark(int countOfFallDown);

    Status getStatus();

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
     * 다음 프레임을 생성한다
     * <p>
     * 볼링은 총 10개 프레임을 가진다
     *
     * @return
     */
    Frame nextFrame();

    FrameData toFrameData();

    /**
     * 현재 프레임까지 스코어를 가져온다
     *
     * @return
     */
    int getScore();


    static Frame first() {
        return new GeneralFrame(1);
    }

    static Frame general(int frameNo) {
        return new GeneralFrame(frameNo);
    }

    static Frame last(int frameNo) {
        return new LastFrame(frameNo);
    }
}
