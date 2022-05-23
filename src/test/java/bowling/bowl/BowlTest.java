package bowling.bowl;

import bowling.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlTest {

    @Test
    @DisplayName("스트라이크 테스트")
    void strike(){
        Bowl bowl = new First();
        Bowl strike = bowl.pitch(new Pins(10));
        assertThat(strike).isExactlyInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("스페어 테스트")
    void spare(){
        Bowl first = new First();
        Bowl second = first.pitch(new Pins(4));
        Bowl spare = second.pitch(new Pins(6));
        assertThat(spare).isExactlyInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("미스 테스트")
    void miss(){
        Bowl first = new First();
        Bowl second = first.pitch(new Pins(4));
        Bowl miss = second.pitch(new Pins(2));
        assertThat(miss).isExactlyInstanceOf(Miss.class);
    }
}