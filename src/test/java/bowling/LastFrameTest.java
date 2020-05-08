package bowling;

import bowling.domain.LastFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {
    private LastFrame lastFrame;

    @BeforeEach
    public void setup() {
        lastFrame = new LastFrame();
    }

    @Test
    @DisplayName("한 번만 던졌을 땐 게임이 종료되지 않는다")
    public void name() {
        lastFrame.bowl(8);

        assertThat(lastFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임에서 미스가 나면 게임이 종료된다")
    public void bowlGameFinishWhenMissed() {
        lastFrame.bowl(8).bowl(1);

        assertThat(lastFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 스트라이크이면 두 번을 더 투구할 수 있다")
    public void bowlOneMoreWhenStrike() {
        lastFrame.bowl(10);

        assertThat(lastFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("스트라이크 후 두 번을 더 투구하면 게임이 끝난다")
    public void bowlTwoMoreWhenStrikeAndGameEnd() {
        lastFrame.bowl(10).bowl(10).bowl(10);

        assertThat(lastFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 스페어이면 한 번을 더 투구할 수 있다")
    public void bowlOneMoreWhenSpare() {
        lastFrame.bowl(9).bowl(1);

        assertThat(lastFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("스페어가 나오고 한 번 더 투구하면 게임이 끝난다")
    public void bowlOneMoreGameEndWhenSpare() {
        lastFrame.bowl(9).bowl(1).bowl(7);

        assertThat(lastFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크를 3번 연속으로 하면 X|X|X를 반환한다")
    public void getRecordWhenStrike() {
        lastFrame.bowl(10).bowl(10).bowl(10);

        assertThat(lastFrame.getRecord()).isEqualTo("X|X|X");
    }

    @Test
    @DisplayName("스페어 처리 후 한 번 더 던진 기록을 반환한다")
    public void getRecordWhenSpare() {
        lastFrame.bowl(7).bowl(3).bowl(10);

        assertThat(lastFrame.getRecord()).isEqualTo("7|/|X");
    }

    @Test
    @DisplayName("미스가 나면 보너스 투구가 없는 기록을 반환한다")
    public void getRecordWhenMiss() {
        lastFrame.bowl(8).bowl(1);

        assertThat(lastFrame.getRecord()).isEqualTo("8|1");
    }
}
