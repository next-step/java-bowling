package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("마지막 프레임 테스트")
class FinalFrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new FinalFrame();
    }

    @DisplayName("프레임의 번호는 10번 이외의 번호를 가질 수 없다.")
    @Test
    void finalFrameException() {
        assertThatThrownBy(() -> new FinalFrame(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크인 경우 한 번 더 투구할 수 있다.")
    @Test
    void fitchStrike() {
        frame.pitch(10);

        assertDoesNotThrow(() -> frame.pitch(5));
    }

    @DisplayName("스패어인 경우 한 번 더 투구할 수 있다.")
    @Test
    void fitchSpare() {
        frame.pitch(7);
        frame.pitch(3);

        assertDoesNotThrow(() -> frame.pitch(5));
    }

    @DisplayName("마지막 프레임에서 다음 프레임은 생성할 수 없다.")
    @Test
    void finalFrameNextException() {
        assertThatThrownBy(() -> frame.nextFrame())
                .isInstanceOf(IllegalStateException.class);
    }
}
