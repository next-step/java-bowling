package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class NormalFrameTest {
    @Test
    @DisplayName("첫번째 투구에서 10개의 핀을 모두 쓰러트리면 스트라이크(X), 프레임 종료")
    public void strikeTest() {
        NormalFrame frame = new NormalFrame();
        frame.setKnockDownPins(10);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("X"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("한 프레임의 모든 투구에서 10개의 핀을 모두 쓰러트리지 못한 경우 점수만 표기, 프레임 종료")
    public void scoreTest() {
        NormalFrame frame = new NormalFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(5);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|5"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("한 프레임의 두번째 투구에서 10개의 핀을 모두 쓰러트린 경우 스페어(/), 프레임 종료")
    public void spareTest() {
        NormalFrame frame = new NormalFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(7);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|/"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-), 프레임 진행")
    public void gutterTest_firstPitcing() {
        NormalFrame frame = new NormalFrame();
        frame.setKnockDownPins(0);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("-"),
                () -> assertThat(frame.isEnd()).isFalse()
        );
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-), 프레임 종료")
    public void gutterTest_secondPitcing() {
        NormalFrame frame = new NormalFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(0);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|-"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }
}
