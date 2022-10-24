package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("일반 프레임 테스트")
class NormalFrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame(1);
    }

    @DisplayName("첫번째 투구에서 모든 핀을 쓰러뜨린 경우 스트라이크이다.")
    @Test
    void fitchStrike() {
        frame.fitch(10);

        assertThat(frame.score()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("스트라이크인 경우 한 번 더 투구할 수 없다.")
    @Test
    void fitchException() {
        frame.fitch(10);

        assertThatThrownBy(() -> frame.fitch(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("두번째 투구에서 모든 핀을 쓰러뜨린 경우 스패어이다.")
    @Test
    void fitchSpare() {
        frame.fitch(7);
        frame.fitch(3);

        assertThat(frame.score()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("스패어 상태에서 한 번 더 투구할 수 없다.")
    @Test
    void fitchSpareException() {
        frame.fitch(7);
        frame.fitch(3);

        assertThatThrownBy(() -> frame.fitch(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("두번째 투구에서도 모든 핀이 쓰러지지 않은 경우 미스이다.")
    @Test
    void fitchMiss() {
        frame.fitch(5);
        frame.fitch(3);

        assertThat(frame.score()).isEqualTo(ScoreType.MISS);
    }

    @DisplayName("미스 상태에서 한 번 더 투구할 수 없다.")
    @Test
    void fitchMissException() {
        frame.fitch(5);
        frame.fitch(3);

        assertThatThrownBy(() -> frame.fitch(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("프레임의 번호는 0번 또는 10번이 될 수 없다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {0, 10})
    void normalFrameException(int input) {
        assertThatThrownBy(() -> new NormalFrame(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("프레임의 번호는 0번 또는 10번이 될 수 없다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {0, 10})
    void finalFrameException(int input) {
        assertThatThrownBy(() -> new NormalFrame(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 투구가 끝나지 않은 상황에서 점수를 내는 경우 예외가 발생한다.")
    @Test
    void scoreException1() {
        assertThatThrownBy(() -> frame.score())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("두 번째 투구가 끝나지 않은 상황에서 점수를 내는 경우 예외가 발생한다.")
    @Test
    void scoreException2() {
        frame.fitch(0);

        assertThatThrownBy(() -> frame.score())
                .isInstanceOf(IllegalStateException.class);
    }
}
