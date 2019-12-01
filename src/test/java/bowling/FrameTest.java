package bowling;

import bowling.domain.Frame;
import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("객체 비교")
    void compareWithFrameObjectTest() {
        Frame frame = Frame.normalFrame(1);
        Frame frame1 = Frame.normalFrame(1);

        assertThat(frame).isEqualTo(frame1);
    }

    @Test
    @DisplayName("적중한 핀 객체 비교")
    void compareWithPinByFrame() {
        Frame frame = Frame.normalFrame(4);
        Pin pin = new Pin(4);

        assertThat(frame.getCountOfHit()).isEqualTo(pin.getCountOfHit());
    }

    @Test
    @DisplayName("프레임 객체에 따른 스코어 테스트")
    void checkScoreByFrameObjectTest() {
        Frame frame = Frame.normalFrame(5);
        Frame frame1 = frame.nextFrame(5);
        Frame strikeFrame = Frame.normalFrame(10);

        assertThat(frame.getScore(0)).isEqualTo("5");
        assertThat(frame1.getScore(5)).isEqualTo("/");
        assertThat(strikeFrame.getScore(0)).isEqualTo("X");
    }

    @Test
    @DisplayName("노말 프레임 스트라이크 프레임 테스트")
    void checkStrikeByNormalFrameTest() {
        Frame frame = Frame.normalFrame(10);
        assertThat(frame.isStrike()).isTrue();
        assertThat(frame.isSecond()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임 스트라이크 프레임 테스트")
    void checkStrikeByFinalFrameTest() {
        Frame frame = Frame.finalFrame(10);
        // 노말 프레임, 스트라이크 프레임 분리 원인
        // 체크하는 메소드가 다름
        assertThat(frame.isRemain()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 보너스 생성 여부 테스트")
    void isBonusFrameByFinalFrameTest() {
        Frame frame = Frame.finalFrame(10);
        Frame frame1 = frame.nextFinalFrame(5);
        assertThat(frame.isBonus(5)).isTrue();

        Frame frame2 = Frame.finalFrame(5);
        Frame frame3 = frame2.nextFinalFrame(5);
        assertThat(frame2.isBonus(5)).isTrue();

        Frame frame4 = frame3.bonusFrame(10);
        assertThat(frame4.getScore(0)).isEqualTo("X");

    }
}
