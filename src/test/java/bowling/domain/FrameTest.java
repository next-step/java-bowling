package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame(1);
    }

    @Test
    @DisplayName("핏칭 스코어의 합이 10을 넘으면 IllegalArgumentException이 발생한다.")
    void pitch() {

        frame.pitch(4);
        assertThatIllegalArgumentException().isThrownBy(() -> frame.pitch(7))
                .withMessage("한 프레임의 점수합이 10을 넘을 수 없습니다.");
    }

    @Test
    @DisplayName("10점 핏칭은 스트라이크임을 확인한다.")
    void isStrike() {
        frame.pitch(10);
        assertTrue(frame.isStrike());
    }

    @ParameterizedTest
    @CsvSource(value = {"1,9", "2,8", "3,7", "4,6", "5,5"})
    @DisplayName("점수 합이 10인 핏칭을 했을 때 스페어임을 확인한다.")
    void isSpare(int first, int second) {
        frame.pitch(first);
        frame.pitch(second);
        assertTrue(frame.isSpare());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("생성된 직후와 0~9까지의 핏칭을 한 프레임은 다음 핏칭이 남았음(remain())을 확인한다.")
    void remain(int score) {
        assertTrue(frame.remain());
        frame.pitch(score);
        assertTrue(frame.remain());
    }

    @Test
    @DisplayName("스트라이크 핏칭을 한 프레임은 다음 핏칭이 남아있지 않다. remain()이 false 이다.")
    void remainFalse() {
        frame.pitch(10);
        assertFalse(frame.remain());
    }

    @Test
    @DisplayName("생성된 직후 frame은 empty 상태임을 확인한다.")
    void empty() {
        assertTrue(frame.empty());
    }
}