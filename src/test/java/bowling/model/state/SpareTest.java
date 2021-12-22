package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    @DisplayName("스페어 표시 테스트")
    void getDescTest() {
        Spare spare = new Spare(Pins.knockedDown(2),Pins.knockedDown(8));
        assertThat(spare.getDesc()).isEqualTo( "/" );
    }
}
