package bowling.bowl;

import bowling.domain.bowl.Miss;
import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.Ready;
import bowling.domain.bowl.Spare;
import bowling.domain.bowl.Strike;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlTest {

    @Test
    @DisplayName("스트라이크 테스트")
    void strike(){
        // given
        Bowl bowl = new Ready();

        // when
        Bowl strike = bowl.pitch(new Pins(10));

        // then
        assertThat(strike).isExactlyInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("스페어 테스트")
    void spare(){
        // given
        Bowl first = new Ready();

        // when
        Bowl second = first.pitch(new Pins(4));
        Bowl spare = second.pitch(new Pins(6));

        // then
        assertThat(spare).isExactlyInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("미스 테스트")
    void miss(){
        // given
        Bowl first = new Ready();

        // when
        Bowl second = first.pitch(new Pins(4));
        Bowl miss = second.pitch(new Pins(2));

        // then
        assertThat(miss).isExactlyInstanceOf(Miss.class);
    }
}