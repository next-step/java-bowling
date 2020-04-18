package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame();
    }

    @Test
    @DisplayName("프레임 생성 테스트")
    void createFrameTest() {
        assertThatCode(
                () -> new NormalFrame()
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("공 던지기 가능 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void isThrowableTrueTest(int firstFallenPin) {
        normalFrame.throwBall(firstFallenPin);
        assertThat(normalFrame.isThrowable()).isTrue();
    }

    @Test
    @DisplayName("공 던지기 불가능 테스트1")
    void isThrowableFalseTest() {
        normalFrame.throwBall(10);
        assertThat(normalFrame.isThrowable()).isFalse();
    }

    @Test
    @DisplayName("공 던지기 불가능 테스트2")
    void isThrowableFalse2Test() {
        normalFrame.throwBall(1);
        normalFrame.throwBall(0);
        assertThat(normalFrame.isThrowable()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("첫번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void firstIsThrowBallTest(int fallenPin) {
        assertThatCode(
                () -> normalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("두번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void secondIsThrowBallTest(int fallenPin) {
        normalFrame.throwBall(5);
        assertThatCode(
                () -> normalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("두번쨰 공 던지기 실패 테스트")
    @ValueSource(ints = {6, 7, 8, 9, 10})
    void failSecondIsThrowBallTest(int fallenPin) {
        normalFrame.throwBall(5);
        assertThatThrownBy(
                () -> normalFrame.throwBall(fallenPin)
        ).isInstanceOf(OverThrowBallException.class);
    }
}
