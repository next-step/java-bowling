package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("볼링 프레임 번호 테스트")
public class FrameNumberTest {

    @DisplayName("프레임 번호가 1 이상 10 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void outOfRangeFrameNumberExceptionTest(int number) {
        // given
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FrameNumber(number))
                .withMessage("프레임 번호는 1 이상 10 이하이어야 합니다.");
    }

    @DisplayName("최초 프레임 번호는 1이다.")
    @Test
    void initialFrameNumberTest() {
        // given, when, then
        assertEquals(FrameNumber.initial(), new FrameNumber(1));
    }

    @DisplayName("next() 메소드를 통해 다음 프레임 번호를 생성할 수 있다.")
    @Test
    void nextFrameNumberTest() {
        // given
        FrameNumber initialFrameNumber = FrameNumber.initial();
        FrameNumber nextFrameNumber = initialFrameNumber.next();

        // when, then
        assertEquals(initialFrameNumber, new FrameNumber(1));
        assertEquals(nextFrameNumber, new FrameNumber(2));
    }

    @DisplayName("canMakeNext() 메소드를 통해 다음 프레임 번호 생성 가능 여부를 조회할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"9:true", "10:false"}, delimiter = ':')
    void canMakeNextFrameNumberTest(int number, boolean result) {
        // given, when, then
        assertSame(new FrameNumber(number).canMakeNext(), result);
    }

    @DisplayName("isNextFinal() 메소드를 통해 다음 프레임 번호가 마지막 번호인지 여부를 조회할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"8:false", "9:true", "10:false"}, delimiter = ':')
    void isNextFinalFrameNumberTest(int number, boolean result) {
        // given, when, then
        assertSame(new FrameNumber(number).isNextFinal(), result);
    }

    @DisplayName("isUnder(), isEqual(), isOver() 메소드를 통해 숫자를 비교할 수 있다.")
    @Test
    void comparingFrameNumberTest() {
        // given
        FrameNumber number = new FrameNumber(5);

        // when, then
        assertTrue(number.isUnder(6));
        assertFalse(number.isUnder(5));

        assertTrue(number.isEqual(5));
        assertFalse(number.isEqual(4));
        assertFalse(number.isEqual(6));

        assertTrue(number.isOver(4));
        assertFalse(number.isOver(5));
    }
}
