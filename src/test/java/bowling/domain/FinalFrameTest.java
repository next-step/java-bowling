package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        frame.bowl(10);

        assertThat(frame.canBowl()).isTrue();
    }

    @DisplayName("스패어인 경우 한 번 더 투구할 수 있다.")
    @Test
    void fitchSpare() {
        frame.bowl(7);
        frame.bowl(3);

        assertDoesNotThrow(() -> frame.bowl(5));
    }


    @DisplayName("투구를 하지 않은 경우 프레임은 끝났다고 판단하지 않는다.")
    @Test
    void endFrame() {
        assertThat(frame.isFinished()).isFalse();
    }

    @DisplayName("투구가 끝나지 않은 상황에서는 프레임은 끝났다고 판단하지 않는다.")
    @Test
    void endFrame1() {
        frame.bowl(5);

        assertThat(frame.isFinished()).isFalse();
    }

    @DisplayName("투구가 끝나면 프레임은 끝났다고 판단한다.")
    @Test
    void endFrame2() {
        frame.bowl(5);
        frame.bowl(3);

        assertThat(frame.isFinished()).isTrue();
    }

    @DisplayName("10|0 인 경우 더 투구할 수 있다.")
    @Test
    void endFrame3() {
        frame.bowl(10);
        frame.bowl(0);

        assertThat(frame.isFinished()).isFalse();
    }

    @DisplayName("10|0|9 인 경우 끝이다.")
    @Test
    void endFrame4() {
        frame.bowl(10);
        frame.bowl(0);
        frame.bowl(9);

        assertThat(frame.isFinished()).isTrue();
    }

    @DisplayName("5|3 인 경우 끝이다.")
    @Test
    void missFrame5() {
        frame.bowl(5);
        frame.bowl(3);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("10|10|10 인 경우 끝이다.")
    @Test
    void endFrame5() {
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);

        assertThat(frame.isFinished()).isTrue();
    }
}
