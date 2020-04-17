package bowling.domain.frame;

import bowling.domain.point.PointOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;


public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame(10);
    }

    @Test
    @DisplayName("프레임 생성 테스트")
    void createFrameTest() {
        assertThatCode(
                () -> new FinalFrame(1)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("첫번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void firstIsThrowBallTest(int fallenPin) {
        assertThatCode(
                () -> finalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("첫번쨰 공 던지기 실패 테스트")
    @ValueSource(ints = {-1, 11})
    void FailFirstIsThrowBallTest(int fallenPin) {
        assertThatThrownBy(
                () -> finalFrame.throwBall(fallenPin)
        ).isInstanceOf(PointOutOfRangeException.class);
    }

    @ParameterizedTest
    @DisplayName("두번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void secondIsThrowBallTest(int fallenPin) {
        finalFrame.throwBall(10);
        assertThatCode(
                () -> finalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("두번쨰 공 던지기 테스트2")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void secondIsThrowBallTest2(int fallenPin) {
        finalFrame.throwBall(5);
        assertThatCode(
                () -> finalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("두번째 공 던지기 실패 테스트")
    void failSecondIsThrowBallTest() {
        finalFrame.throwBall(7);

        assertThatThrownBy(
                () -> finalFrame.throwBall(6)
        ).isInstanceOf(OverThrowBallException.class);
    }

    @ParameterizedTest
    @DisplayName("세번쨰 공 던지기 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void thirdIsThrowBallTest(int fallenPin) {
        finalFrame.throwBall(5);
        finalFrame.throwBall(5);
        assertThatCode(
                () -> finalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("세번쨰 공 던지기 가능 테스트2")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void thirdIsThrowBallTest2(int fallenPin) {
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        assertThatCode(
                () -> finalFrame.throwBall(fallenPin)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("네번쨰 공 던지기 가능 테스트")
    void fourthIsThrowBallTest() {
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        assertThat(
                finalFrame.isThrowable()
        ).isFalse();
    }
}
