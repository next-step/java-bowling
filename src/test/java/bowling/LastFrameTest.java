package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {
    @Test
    @DisplayName("마지막 프레임에서 미스가 나면 게임이 종료된다")
    public void bowlGameFinishWhenMissed() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.bowl(8).bowl(1);

        assertThat(lastFrame.isGameEnd()).isTrue();
    }
}
