package bowling.domain.frame;

import bowling.domain.pin.PinCount;
import bowling.domain.state.StateExpression;
import bowling.fixture.FinalFrameFixture;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class FinalFrameTest {

    @DisplayName("생성 성공")
    @Test
    public void ofFirst() {
        assertThatCode(FinalFrame::newInstance)
                .doesNotThrowAnyException();
    }

    @DisplayName("자신의 프레임 번호 반환 : 항상 MAX_NUMBER")
    @Test
    public void getNo() {
        assertThat(FinalFrame.newInstance().getFrameNo())
                .isEqualTo(FrameNumber.MAX_NUMBER);
    }

    @DisplayName("현재 상태가 종료 상태의 경우, 상태값을 추가")
    @Test
    public void finishedAfterBowl() {
        Frame nextFrame = FinalFrame.newInstance();
        nextFrame.bowl(PinCount.of(PinCount.MAX_COUNT));

        assertThat(nextFrame.getFrameResult().getSymbol())
                .isEqualTo(StateExpression.STRIKE);
    }

    @DisplayName("현재 상태가 진행 상태의 경우, 현재 상태를 제거하고 새로 던진 볼링공의 상태를 추가 및 상태값 반환")
    @ParameterizedTest
    @MethodSource
    public void runningAfterBowl(final Frame nextFrame, final String expected) {
        assertThat(nextFrame.getFrameResult().getSymbol())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> runningAfterBowl() {
        return Stream.of(
                Arguments.of(FinalFrameFixture.getOneStrikeFrame(), "X"),
                Arguments.of(FinalFrameFixture.getStrikeGutterFrame(), "X|-"),
                Arguments.of(FinalFrameFixture.getHitSpareStrikeFrame(), "9|/|X")
        );
    }

    @DisplayName("게임 종료 여부 판단 상태")
    @ParameterizedTest
    @MethodSource
    public void isGameOver(final Frame frame) {
        assertThat(frame.isGameOver())
                .isTrue();
    }

    private static Stream<Arguments> isGameOver() {
        return Stream.of(
                Arguments.of(FinalFrameFixture.getThreeStrikeFrame()),
                Arguments.of(FinalFrameFixture.getHitSpareStrikeFrame()),
                Arguments.of(FinalFrameFixture.getHitSpareHitFrame()),
                Arguments.of(FinalFrameFixture.getHitMissFrame()),
                Arguments.of(FinalFrameFixture.getStrikeHitMissFrame())
        );
    }
}
