package bowling.model.state.finished;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {
    @Test
    void of_정상() {
        assertThat(Spare.of(Pins.from(0), Pins.from(10)));
    }

    @Test
    void of_Spare의_조건에_맞지_않는_경우() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.of(Pins.from(10), Pins.from(0)))
                .withMessage("Spare의 조건에 맞지 않습니다.");
    }

    @Test
    void isFinished() {
        assertThat(Spare.of(Pins.from(8), Pins.from(2)).isFinished()).isTrue();
    }

    @Test
    void bowling() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.of(Pins.from(8), Pins.from(2)).bowling(1))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }
}