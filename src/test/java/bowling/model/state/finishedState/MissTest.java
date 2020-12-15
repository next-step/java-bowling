package bowling.model.state.finishedState;

import bowling.model.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {

    @Test
    void of_정상() {
        assertThat(Miss.from(Score.from(9)));
    }

    @Test
    void of_Miss의_조건에_맞지_않는_경우() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.from(Score.from(10)))
                .withMessage("Miss의 조건에 맞지 않습니다.");
    }

    @Test
    void isFinished() {
        assertThat(Miss.from(Score.from(9)).isFinished()).isTrue();
    }

    @Test
    void bowling() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.from(Score.from(9)).bowling(1))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }
}