package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("볼링 프레임에서 쓰러진 핀 테스트")
public class FrameFallenPinTest {

    @DisplayName("프레임의 첫 투구 후에 두 번째 FallenPin 객체는 비어있어야 한다.")
    @Test
    void emptySecondFallenPinOfFirstFrameFallenPinTest() {
        // given, when
        FrameFallenPin firstFrameFallenPin = FrameFallenPin.first(5);

        // then
        assertNull(firstFrameFallenPin.second());
    }

    @DisplayName("프레임을 두 번 투구 후에 첫 번째 FallenPin 객체는 비어있으면 안된다.")
    @Test
    void notEmptyFirstFallenPinOfSecondFrameFallenPinTest() {
        // given, when
        FrameFallenPin firstFrameFallenPin = FrameFallenPin.first(5);
        FrameFallenPin secondFrameFallenPin = firstFrameFallenPin.second(5);

        // then
        assertNotSame(secondFrameFallenPin.first(), null);
    }

    @DisplayName("이미 스트라이크인데 두 번째 쓰러진 핀을 생성하려고 하면 예외가 발생한다.")
    @Test
    void createSecondFallenPinOfStrikeFrameFallenPinExceptionTest() {
        // given
        FrameFallenPin strikeFrameFallenPin = FrameFallenPin.first(10);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> strikeFrameFallenPin.second(0))
                .withMessage("이미 스트라이크이기 때문에 두 번째로 쓰러진 핀을 생성할 수 없습니다.");
    }

    @DisplayName("프레임의 쓰러진 핀의 개수 총합이 10개을 초과면 예외가 발생한다.")
    @Test
    void fallenPinTotalOverMaxExceptionTest() {
        // given
        FrameFallenPin frameFallenPin = FrameFallenPin.first(5);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameFallenPin.second(6))
                .withMessage("총 쓰러진 핀의 개수가 10개를 초과할 수 없습니다.");
    }

    @DisplayName("isStrike() 메소드를 통해 스트라이크 여부를 조회할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"10:true", "9:false"}, delimiter = ':')
    void isStrikeFrameFallenPinTest(int firstFallenPinCount, boolean isStrike) {
        // given
        FrameFallenPin frameFallenPin = FrameFallenPin.first(firstFallenPinCount);

        // when, then
        assertEquals(frameFallenPin.isStrike(), isStrike);
    }

    @DisplayName("isSpare() 메소드를 통해 스페어 여부를 조회할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"8:2:false:true", "8:1:false:false"}, delimiter = ':')
    void isSpareFrameFallenPinTest(int firstFallenPinCount, int secondFallenPinCount, boolean isFirstFallenPinStrike,
                                   boolean isSecondFallenPinStrike) {
        // given
        FrameFallenPin firstFrameFallenPin = FrameFallenPin.first(firstFallenPinCount);
        FrameFallenPin secondFrameFallenPin = firstFrameFallenPin.second(secondFallenPinCount);

        // when, then
        assertEquals(firstFrameFallenPin.isSpare(), isFirstFallenPinStrike);
        assertEquals(secondFrameFallenPin.isSpare(), isSecondFallenPinStrike);
    }


    @DisplayName("pitchTwice() 메소드를 통해 핀이 두 번 쓰러졌는지 여부를 조회할 수 있다.")
    @Test
    void pitchTwiceFrameFallenPinTest() {
        // given
        FrameFallenPin firstFrameFallenPin = FrameFallenPin.first(5);
        FrameFallenPin secondFrameFallenPin = firstFrameFallenPin.second(5);

        // when, then
        assertFalse(firstFrameFallenPin.pitchTwice());
        assertTrue(secondFrameFallenPin.pitchTwice());
    }

    @DisplayName("isFirst() 메소드를 통해 핀이 한 번 쓰러졌는지 여부를 조회할 수 있다.")
    @Test
    void isFirstFrameFallenPinTest() {
        // given
        FrameFallenPin firstFrameFallenPin = FrameFallenPin.first(5);
        FrameFallenPin secondFrameFallenPin = firstFrameFallenPin.second(5);

        // when, then
        assertTrue(firstFrameFallenPin.isFirst());
        assertFalse(secondFrameFallenPin.isFirst());
    }

    @DisplayName("isXXXGutter() 메소드를 통해 거터 여부를 조회할 수 있다.")
    @Test
    void isGutterFrameFallenPinTest() {
        // given
        FrameFallenPin firstGutterFrameFallenPin = FrameFallenPin.first(0);
        FrameFallenPin doubleGutterFrameFallenPin = firstGutterFrameFallenPin.second(0);

        // when, then
        assertTrue(firstGutterFrameFallenPin.isFirstAndGutter());
        assertTrue(doubleGutterFrameFallenPin.isDoubleGutter());
    }

    @DisplayName("countTotal() 메소드를 통해 쓰러뜨린 핀 개수의 총합을 구할 수 있다.")
    @Test
    void countTotalOfFrameFallenPinTest() {
        // given
        FrameFallenPin firstFrameFallenPin = FrameFallenPin.first(5);
        FrameFallenPin secondFrameFallenPin = firstFrameFallenPin.second(5);

        // when, then
        assertEquals(firstFrameFallenPin.countTotal(), 5);
        assertEquals(secondFrameFallenPin.countTotal(), 10);
    }
}
