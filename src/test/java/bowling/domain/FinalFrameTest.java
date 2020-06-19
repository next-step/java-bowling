package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("스트라이크의 경우 1번 더 칠 수 있도록 현재 Frame 반환")
    @Test
    public void nextFinalFrame_스트라이크_보너스() {
        Frame finalFrame = FinalFrame.last(10);

        finalFrame.bowl(10);

        assertThat(finalFrame.next()).isEqualTo(finalFrame);
    }

    @DisplayName("스페어의 경우 1번 더 칠 수 있도록 현재 Frame 반환")
    @Test
    public void nextFinalFrame_스페어() {
        Frame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(2);
        finalFrame.bowl(8);

        assertThat(finalFrame.next()).isEqualTo(finalFrame);
    }

    @DisplayName("그 외의 경우 경기 종료를 위해 null 반환")
    @Test
    public void nextFinalFrame_경기종료() {
        Frame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(2);
        finalFrame.bowl(4);

        assertThat(finalFrame.next()).isEqualTo(null);
    }
}
