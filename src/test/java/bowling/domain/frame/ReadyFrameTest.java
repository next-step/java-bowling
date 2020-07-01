package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.exception.CannotCreateNextFrameException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class ReadyFrameTest {

    @DisplayName("생성 성공")
    @Test
    public void newInstance() {
        assertThatCode(ReadyFrame::newInstance)
                .doesNotThrowAnyException();
    }

    @DisplayName("게임 플레이어 turn 변경 여부 반환 : 항상 false")
    @Test
    public void isTurnOver() {
        assertThat(ReadyFrame.newInstance().isTurnOver())
                .isFalse();
    }

    @DisplayName("다음 프레임 반환 : 예외 반환")
    @Test
    public void initNextFrame() {
        assertThatExceptionOfType(CannotCreateNextFrameException.class)
                .isThrownBy(() -> ReadyFrame.newInstance().initNextFrame());
    }

    @DisplayName("현재 프레임 번호 : 항상 -1")
    @Test
    public void getFrameNo() {
        assertThat(ReadyFrame.newInstance().getFrameNo())
                .isEqualTo(-1);
    }

    @DisplayName("쓰러진 공에 따라 상태값이 변경 및 자신의 상태값을 반환 : 항상 null 반환")
    @Test
    public void getFrameResult() {
        assertThat(ReadyFrame.newInstance().getFrameResult())
                .isNull();
    }

    @DisplayName("해당 프레임의 점수를 반환")
    @Test
    public void getScore() {
        assertThat(ReadyFrame.newInstance().getScore())
                .isEqualTo(Score.UN_SCORE);
    }

    @DisplayName("여분의 보너스 점수를 해결하기 위한 추가적인 점수를 계산하여 반환 : 항상 이전의 점수 반환")
    @ParameterizedTest
    @MethodSource
    public void addBonusScore(final Score beforeScore, final Score expected) {
        assertThat(ReadyFrame.newInstance().addBonusScore(beforeScore))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> addBonusScore() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), Score.ofStrike()),
                Arguments.of(Score.ofMiss(5), Score.ofMiss(5)),
                Arguments.of(Score.ofSpare(), Score.ofSpare()),
                Arguments.of(Score.valueOf(20, 0), Score.valueOf(20, 0))
        );
    }
}
