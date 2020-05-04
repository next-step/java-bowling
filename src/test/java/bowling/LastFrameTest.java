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

    @Test
    @DisplayName("마지막 프레임에서 스트라이크이면 한 번을 더 투구할 수 있다")
    public void bowlOneMoreWhenStrike() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.bowl(10);

        assertThat(lastFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임에서 스페어이면 한 번을 더 투구할 수 있다")
    public void bowlOneMoreWhenSpare() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.bowl(9).bowl(1);

        assertThat(lastFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("스페어가 나오고 한 번 더 투구하면 게임이 끝난다")
    public void bowlOneMoreGameEndWhenSpare() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.bowl(9).bowl(1).bowl(7);

        assertThat(lastFrame.isGameEnd()).isTrue();
    }
}
