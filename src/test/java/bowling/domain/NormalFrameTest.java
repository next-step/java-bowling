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
        frame = new NormalFrame();
    }

    @DisplayName("스트라이크인 경우 한 번 더 투구할 수 없다.")
    @Test
    void bowlException() {
        frame.bowl(10);

        assertThat(frame.canPitch()).isFalse();
    }

    @DisplayName("스패어 상태에서 한 번 더 투구할 수 없다.")
    @Test
    void bowlSpareException() {
        frame.bowl(7);
        frame.bowl(3);

        assertThat(frame.canPitch()).isFalse();
    }

    @DisplayName("투구가 끝난 상황에서 한 번 더 투구할 수 없다.")
    @Test
    void bowlMissException() {
        frame.bowl(5);
        frame.bowl(3);

        assertThat(frame.canPitch()).isFalse();
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

    @DisplayName("다음 프레임을 생성 할 수 있다.")
    @Test
    void next() {
        Frame nextFrame = frame.nextFrame();

        assertThat(nextFrame.number()).isEqualTo(2);
    }

    @DisplayName("마지막 프레임을 생성 할 수 있다.")
    @Test
    void nextFinal() {
        Frame finalFrame = new NormalFrame(9).nextFrame();

        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }
}
