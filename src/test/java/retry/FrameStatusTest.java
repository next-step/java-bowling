package retry;

import bowling.retry.FrameStatus;
import bowling.retry.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {

    @Test
    @DisplayName("프레임 스트라이크 상태 체크")
    void getFrameStatusByStrike() {
        LinkedList<Pin> pins = new LinkedList<>();
        pins.offer(new Pin(10));

        FrameStatus frameStatus = new FrameStatus(pins);

        assertThat(frameStatus.getStatus()).isEqualTo("  X  |");
    }

    @Test
    @DisplayName("프레임 스페어 상태 체크")
    void getFrameStatusBySpare() {
        LinkedList<Pin> pins = new LinkedList<>();
        pins.offer(new Pin(4));
        pins.offer(new Pin(6));

        FrameStatus frameStatus = new FrameStatus(pins);
        assertThat(frameStatus.getStatus()).isEqualTo("  4|/  |");
    }

    @Test
    @DisplayName("프레임 거터 상태 체크")
    void getFrameStatusByGutter() {
        LinkedList<Pin> pins = new LinkedList<>();
        pins.offer(new Pin(0));

        FrameStatus frameStatus = new FrameStatus(pins);
        assertThat(frameStatus.getStatus()).isEqualTo("  -  |");
    }

    @Test
    @DisplayName("프레임 거터 상태 체크2")
    void getFrameStatusByGutter2() {
        LinkedList<Pin> pins = new LinkedList<>();
        pins.offer(new Pin(0));
        pins.offer(new Pin(0));

        FrameStatus frameStatus = new FrameStatus(pins);
        assertThat(frameStatus.getStatus()).isEqualTo("  -|-  |");
    }
}
