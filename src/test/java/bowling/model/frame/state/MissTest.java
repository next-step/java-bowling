package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {

    @Test
    void getScore_success() {
        // given
        Pins first = Pins.valueOf(0);
        Pins second = Pins.valueOf(1);

        // when
        State result = Miss.valueOf(first, second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

    @Test
    void createMiss_whenFirstSumSecond_fail() {
        // given
        Pins first = Pins.valueOf(Pins.MIN);
        Pins second = Pins.valueOf(Pins.MAX);

        // when
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.valueOf(first, second));
    }

    @Test
    void printResult_success() {
        // given
        Pins first = Pins.valueOf(0);
        Pins second = Pins.valueOf(1);

        // when
        String print = Miss.valueOf(first, second).printResult();

        // then
        assertThat(print).isEqualTo("   0|1   ");
    }
}