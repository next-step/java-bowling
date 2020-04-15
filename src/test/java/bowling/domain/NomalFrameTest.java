package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class NomalFrameTest {
    private NomalFrame nomalFrame;

    @BeforeEach
    void setUp() {
        nomalFrame = new NomalFrame(1);
    }

    @Test
    @DisplayName("프레임 생성 테스트")
    void createFrameTest() {
        assertThatCode(
                () -> new NomalFrame(1)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("공 던지기 가능 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void isThrowableTrueTest(int firstFallenPin) {
        nomalFrame.throwBall(firstFallenPin);
        assertThat(nomalFrame.isThrowable()).isTrue();
    }

    @Test
    @DisplayName("공 던지기 불가능 테스트1")
    void isThrowableFalseTest() {
        nomalFrame.throwBall(10);
        assertThat(nomalFrame.isThrowable()).isFalse();
    }

    @Test
    @DisplayName("공 던지기 불가능 테스트2")
    void isThrowableFalse2Test() {
        nomalFrame.throwBall(1);
        nomalFrame.throwBall(0);
        assertThat(nomalFrame.isThrowable()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("첫번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void firstIsThrowBallTest(int fallenPin) {
        assertThatCode(
                () -> nomalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("두번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void secondIsThrowBallTest(int fallenPin) {
        nomalFrame.throwBall(5);
        assertThatCode(
                () -> nomalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("두번쨰 공 던지기 실패 테스트")
    @ValueSource(ints = {6, 7, 8, 9, 10})
    void failSecondIsThrowBallTest(int fallenPin) {
        nomalFrame.throwBall(5);
        assertThatThrownBy(
                () -> nomalFrame.throwBall(fallenPin)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
