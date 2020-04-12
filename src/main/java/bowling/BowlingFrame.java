package bowling;

public interface BowlingFrame {
    /*
     * 볼링 게임의 프레임을 관리한다.
     * FrameScore와 Pins를 관리한다.
     * 쓰러트린 볼링 핀수를 입력받아 FrameScore과 Pins를 갱신한다.
     */

    void bowl(int scoreCount);

    boolean isOver();

    int sum();

    FrameScore getFrameScore();
}
