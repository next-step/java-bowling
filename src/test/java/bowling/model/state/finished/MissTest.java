package bowling.model.state.finished;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {

    @Test
    void of_정상() {
        assertThat(Miss.of(Pins.from(0), Pins.from(9)));
    }

    @Test
    void of_Miss의_조건에_맞지_않는_경우() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.of(Pins.from(0), Pins.from(10)))
                .withMessage("Miss의 조건에 맞지 않습니다.");
    }

    @Test
    void isFinished() {
        assertThat(Miss.of(Pins.from(0), Pins.from(9)).isFinished()).isTrue();
    }

    @Test
    void bowling() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.of(Pins.from(0), Pins.from(9)).bowling(Pins.from(1)))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }
}