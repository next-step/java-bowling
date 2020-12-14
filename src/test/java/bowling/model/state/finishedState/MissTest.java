package bowling.model.state.finishedState;

import bowling.model.Score;
import bowling.model.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @Test
    void of_정상() {
        assertThat(Miss.of(Score.from(0), Score.from(9)));
    }

    @ParameterizedTest
    @MethodSource("missParams")
    void of_Miss의_조건에_맞지_않는_경우(Score previous, Score totalScore) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.of(previous, totalScore))
                .withMessage("Miss의 조건에 맞지 않습니다.");
    }

    @Test
    void of_이전_스코어가_합_스코어보다_큰_경우() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.of(Score.from(9), Score.from(8)))
                .withMessage("이전 스코어는 합보다 클 수 없습니다.");
    }

    @Test
    void isFinished() {
        assertThat(Miss.of(Score.from(8), Score.from(9)).isFinished()).isTrue();
    }

    @Test
    void bowling() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.of(Score.from(8), Score.from(9)).bowling(1))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }

    private static Stream<Arguments> missParams() {
        return Stream.of(
                Arguments.of(Score.from(10), Score.from(10)),
                Arguments.of(Score.from(1), Score.from(10))
        );
    }
}