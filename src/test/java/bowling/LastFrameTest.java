package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

//todo isEnd 검증과 stauts 검증 분리
public class LastFrameTest {
    @Test
    @DisplayName("첫번째 투구에서 10개의 핀을 모두 쓰러트리면 스트라이크(X), 프레임 계속 진행")
    public void strikeTest() {
        LastFrame frame = new LastFrame();
        frame.setKnockDownPins(10);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("X"),
                () -> assertThat(frame.isEnd()).isFalse()
        );
    }

    @Test
    @DisplayName("한 프레임의 모든 투구에서 10개의 핀을 모두 쓰러트리지 못한 경우 점수만 표기, 프레임 종료")
    public void scoreTest() {
        LastFrame frame = new LastFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(5);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|5"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("한 프레임의 두번째 투구에서 10개의 핀을 모두 쓰러트린 경우 스페어(/), 프레임 계속 진행")
    public void spareTest() {
        LastFrame frame = new LastFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(7);
        assertThat(frame.getStatus()).isEqualTo("3|/");

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|/"),
                () -> assertThat(frame.isEnd()).isFalse()
        );
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-), 프레임 계속 진행")
    public void gutterTest_secondPitching() {
        LastFrame frame = new LastFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(0);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|-"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 투구의 경우 거터(-), 프레임 종료")
    public void gutterTest_thirdPitching() {
        LastFrame frame = new LastFrame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(7);
        frame.setKnockDownPins(0);

        assertAll(
                () -> assertThat(frame.getStatus()).isEqualTo("3|/|-"),
                () -> assertThat(frame.isEnd()).isTrue()
        );
    }
}
