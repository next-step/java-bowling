package bowling;

import bowling.entity.Pin;
import bowling.entity.frame.LastFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LastFrameTest {
    @Test
    @DisplayName("2번 진행 시 종료")
    public void twoBowlGameEnd() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.bowl(new Pin(5));
        lastFrame.bowl(new Pin(4));

        assertThat(lastFrame.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("3번 진행 시 종료")
    public void threeBowlGameEnd() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.bowl(new Pin(10));
        lastFrame.bowl(new Pin(5));
        lastFrame.bowl(new Pin(4));

        assertThat(lastFrame.bowlingGameEnd()).isTrue();
    }
}
