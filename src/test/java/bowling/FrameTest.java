package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;


public class FrameTest {
    @Test
    @DisplayName("한번에 10개의 핀을 쓰러트리면 스트라이크")
    public void strikeTest() {
        Frame frame = new Frame();
        frame.setKnockDownPins(10);
        assertThat(frame.getStatus()).isEqualTo("X");
    }
}
