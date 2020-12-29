package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameStatusTest {

    @Test
    @DisplayName("스트라이크 상태")
    void testStrike() {
        List<DownedPin> downedPins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(10);
        downedPins.add(firstPitch);

        assertThat(FrameStatus.decideStatus(downedPins))
                .isEqualTo(FrameStatus.STRIKE);
    }

    @Test
    @DisplayName("스페어 상태")
    void testIstStrike() {
        List<DownedPin> downedPins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(6);
        DownedPin secondPitch = DownedPin.fromNumber(4);
        downedPins.add(firstPitch);
        downedPins.add(secondPitch);

        assertThat(FrameStatus.decideStatus(downedPins))
                .isEqualTo(FrameStatus.SPARE);
    }

    @Test
    @DisplayName("미스 상태")
    void testIsMiss() {
        List<DownedPin> downedPins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(6);
        DownedPin secondPitch = DownedPin.fromNumber(3);
        downedPins.add(firstPitch);
        downedPins.add(secondPitch);

        assertThat(FrameStatus.decideStatus(downedPins))
                .isEqualTo(FrameStatus.MISS);
    }

    @Test
    @DisplayName("아직 투구하지 않은 상태")
    void testIsStart() {
        List<DownedPin> downedPins = new ArrayList<>();

        assertThat(FrameStatus.decideStatus(downedPins))
                .isEqualTo(FrameStatus.START);
    }

    @Test
    @DisplayName("1회 투구 이후 종료되지 않았을 경우")
    void testOnFrame() {
        List<DownedPin> downedPins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(6);
        downedPins.add(firstPitch);

        assertThat(FrameStatus.decideStatus(downedPins))
                .isEqualTo(FrameStatus.ON_FRAME);
    }

    @Test
    @DisplayName("2회를 일반적으로 종료했을 경우")
    void testOnMiss() {
        Frame frame = new Frame();
        frame.record(7);
        frame.record(2);

        assertThat(frame.decideStatus())
                .isEqualTo(FrameStatus.MISS);
    }
}
