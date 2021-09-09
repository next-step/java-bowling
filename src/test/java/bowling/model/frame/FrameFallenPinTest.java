package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("볼링 프레임에서 쓰러진 핀 테스트")
public class FrameFallenPinTest {

    @DisplayName("프레임이 가지고 있는 쓰러진 핀의 첫 번째 객체의 두 번째 쓰러진 핀은 비어있어야 한다.")
    @Test
    void emptySecondFallenPinOfFrameFallenPinFirstTest() {
        // given, when
        FrameFallenPin frameFallenPinFirst = FrameFallenPin.first(5);

        // then
        assertSame(frameFallenPinFirst.getSecond(), null);
    }

    @DisplayName("프레임이 가지고 있는 쓰러진 핀의 두 번째 객체의 첫 번째 쓰러진 핀이 비어있으면 안된다.")
    @Test
    void notEmptyFirstFallenPinOfFrameFallenPinSecondTest() {
        // given, when
        FrameFallenPin frameFallenPinFirst = FrameFallenPin.first(5);
        FrameFallenPin frameFallenPinSecond = frameFallenPinFirst.second(5);

        // then
        assertNotSame(frameFallenPinSecond.getFirst(), null);
    }

    @DisplayName("이미 스트라이크인데 두 번째 쓰러진 핀을 생성하려고 하면 예외가 발생한다.")
    @Test
    void createSecondFallenPinOfStrikeFrameExceptionTest() {
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
    @Test
    void isStrikeFrameFallenPinTest() {
        // given
        FrameFallenPin strike = FrameFallenPin.first(10);
        FrameFallenPin notStrike = FrameFallenPin.first(9);

        // when, then
        assertTrue(strike.isStrike());
        assertFalse(notStrike.isStrike());
    }

    @DisplayName("isSpare() 메소드를 통해 스페어 여부를 조회할 수 있다.")
    @Test
    void isSpareFrameFallenPinTest() {
        // given
        FrameFallenPin first = FrameFallenPin.first(8);
        FrameFallenPin spare = first.second(2);
        FrameFallenPin notSpare = first.second(1);

        // when, then
        assertFalse(first.isSpare());
        assertTrue(spare.isSpare());
        assertFalse(notSpare.isSpare());
    }

    @DisplayName("isMiss() 메소드를 통해 미스 여부를 조회할 수 있다.")
    @Test
    void isMissFrameFallenPinTest() {
        // given
        FrameFallenPin first = FrameFallenPin.first(5);
        FrameFallenPin miss = first.second(3);
        FrameFallenPin notMiss = first.second(5);

        // when, then
        assertFalse(first.isMiss());
        assertTrue(miss.isMiss());
        assertFalse(notMiss.isMiss());
    }

    @DisplayName("pitchTwice() 메소드를 통해 핀이 두 번 쓰러졌는지 여부를 조회할 수 있다.")
    @Test
    void pitchTwiceFrameFallenPinTest() {
        // given
        FrameFallenPin first = FrameFallenPin.first(5);
        FrameFallenPin second = first.second(5);

        // when, then
        assertFalse(first.pitchTwice());
        assertTrue(second.pitchTwice());
    }

    @DisplayName("isFirst() 메소드를 통해 핀이 한 번 쓰러졌는지 여부를 조회할 수 있다.")
    @Test
    void isFirstFrameFallenPinTest() {
        // given
        FrameFallenPin first = FrameFallenPin.first(5);
        FrameFallenPin second = first.second(5);

        // when, then
        assertTrue(first.isFirst());
        assertFalse(second.isFirst());
    }

    @DisplayName("isXXXGutter() 메소드를 통해 거터 여부를 조회할 수 있다.")
    @Test
    void isGutterFrameFallenPinTest() {
        // given
        FrameFallenPin firstGutter = FrameFallenPin.first(0);
        FrameFallenPin doubleGutter = firstGutter.second(0);

        // when, then
        assertTrue(firstGutter.isFirstAndGutter());
        assertTrue(doubleGutter.isDoubleGutter());
    }
}
