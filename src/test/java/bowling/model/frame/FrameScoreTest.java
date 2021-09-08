package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.frame.FrameScore.EMPTY_SCORE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("볼링 프레임 점수 테스트")
public class FrameScoreTest {

    @DisplayName("프레임 점수 첫 번째 객체의 두 번째 점수값은 비어있어야 한다.")
    @Test
    void emptySecondScoreOfFrameScoreFirstTest() {
        // given, when
        FrameScore frameScoreFirst = FrameScore.first(5);

        // then
        assertSame(frameScoreFirst.getSecond(), EMPTY_SCORE);
    }

    @DisplayName("프레임 점수 두 번째 객체의 첫 번째 점수값이 비어있으면 안된다.")
    @Test
    void notEmptyFirstScoreOfFrameScoreSecondTest() {
        // given, when
        FrameScore frameScoreFirst = FrameScore.first(5);
        FrameScore frameScoreSecond = frameScoreFirst.second(5);

        // then
        assertNotSame(frameScoreSecond.getFirst(), EMPTY_SCORE);
    }

    @DisplayName("이미 스트라이크인데 두 번째 점수를 생성하려고 하면 예외가 발생한다.")
    @Test
    void createSecondScoreOfStrikeFrameExceptionTest() {
        // given
        FrameScore strikeFrameScore = FrameScore.first(10);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> strikeFrameScore.second(0))
                .withMessage("이미 스트라이크이기 때문에 두 번째 점수를 생성할 수 없습니다.");
    }

    @DisplayName("프레임 점수의 총합이 10점을 초과면 예외가 발생한다.")
    @Test
    void totalScoreOverMaxExceptionTest() {
        // given
        FrameScore frameScore = FrameScore.first(5);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameScore.second(6))
                .withMessage("총 볼링 점수가 10점을 초과할 수 없습니다.");
    }

    @DisplayName("isStrike() 메소드를 통해 스트라이크 여부를 조회할 수 있다.")
    @Test
    void isStrikeFrameScoreTest() {
        // given
        FrameScore strikeScore = FrameScore.first(10);
        FrameScore notStrikeScore = FrameScore.first(9);

        // when, then
        assertTrue(strikeScore.isStrike());
        assertFalse(notStrikeScore.isStrike());
    }

    @DisplayName("isSpare() 메소드를 통해 스페어 여부를 조회할 수 있다.")
    @Test
    void isSpareFrameScoreTest() {
        // given
        FrameScore firstScore = FrameScore.first(8);
        FrameScore spareScore = firstScore.second(2);
        FrameScore notSpareScore = firstScore.second(1);

        // when, then
        assertFalse(firstScore.isSpare());
        assertTrue(spareScore.isSpare());
        assertFalse(notSpareScore.isSpare());
    }

    @DisplayName("isMiss() 메소드를 통해 미스 여부를 조회할 수 있다.")
    @Test
    void isMissFrameScoreTest() {
        // given
        FrameScore firstScore = FrameScore.first(5);
        FrameScore missScore = firstScore.second(3);
        FrameScore notMissScore = firstScore.second(5);

        // when, then
        assertFalse(firstScore.isMiss());
        assertTrue(missScore.isMiss());
        assertFalse(notMissScore.isMiss());
    }

    @DisplayName("pitchTwice() 메소드를 통해 두 번 던진 점수 여부를 조회할 수 있다.")
    @Test
    void pitchTwiceFrameScoreTest() {
        // given
        FrameScore firstScore = FrameScore.first(5);
        FrameScore secondScore = firstScore.second(5);

        // when, then
        assertFalse(firstScore.pitchTwice());
        assertTrue(secondScore.pitchTwice());
    }

    @DisplayName("isFirst() 메소드를 통해 프레임 첫 점수인지 여부를 조회할 수 있다.")
    @Test
    void isFirstFrameScoreTest() {
        // given
        FrameScore firstScore = FrameScore.first(5);
        FrameScore secondScore = firstScore.second(5);

        // when, then
        assertTrue(firstScore.isFirst());
        assertFalse(secondScore.isFirst());
    }

    @DisplayName("isXXXGutter() 메소드를 통해 거터 여부를 조회할 수 있다.")
    @Test
    void isGutterFrameScoreTest() {
        // given
        FrameScore firstGutter = FrameScore.first(0);
        FrameScore doubleGutter = firstGutter.second(0);

        // when, then
        assertTrue(firstGutter.isFirstAndGutter());
        assertTrue(doubleGutter.isDoubleGutter());
    }
}
