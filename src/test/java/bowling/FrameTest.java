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
        Frame frame = Frame.frame(1);
        Frame frame1 = Frame.frame(1);

        assertThat(frame).isEqualTo(frame1);
    }

    @Test
    @DisplayName("적중한 핀 객체 비교")
    void compareWithPinByFrame() {
        Frame frame = Frame.frame(4);
        Pin pin = new Pin(4);

        assertThat(frame.getCountOfHit()).isEqualTo(pin.getCountOfHit());
    }

    @Test
    @DisplayName("프레임 객체에 따른 스코어 테스트")
    void checkScoreByFrameObjectTest() {
        Frame frame = Frame.frame(5);
        Frame frame1 = frame.nextFrame(5);
        Frame strikeFrame = Frame.frame(10);

        assertThat(frame.getScoreByNormalFrame(0)).isEqualTo("5");
        assertThat(frame1.getScoreByNormalFrame(5)).isEqualTo("/");
        assertThat(strikeFrame.getScoreByNormalFrame(0)).isEqualTo("X");
    }

    @Test
    @DisplayName("노말 프레임 스트라이크 프레임 테스트")
    void checkStrikeByNormalFrameTest() {
        Frame frame = Frame.frame(10);
        assertThat(frame.isStrike()).isTrue();
        assertThat(frame.isRemain() && !frame.isStrike()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임 스트라이크 프레임 테스트")
    void checkStrikeByFinalFrameTest() {
        Frame frame = Frame.frame(10);
        assertThat(frame.isRemain()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 보너스 생성 여부 테스트")
    void isBonusFrameByFinalFrameTest() {
        Frame frame = Frame.frame(10);
        Frame frame1 = frame.nextFinalFrame(5);
        assertThat(frame.isBonus(5)).isTrue();

        Frame frame2 = Frame.frame(5);
        Frame frame3 = frame2.nextFinalFrame(5);
        assertThat(frame2.isBonus(5)).isTrue();

        Frame frame4 = frame3.bonusFrame(10);
        assertThat(frame4.getScoreByFinalFrame(0)).isEqualTo("X");

    }
}
