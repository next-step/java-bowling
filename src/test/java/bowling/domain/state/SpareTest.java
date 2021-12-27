package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {

    @Test
    @DisplayName("스페어 표시 테스트")
    void markTest() {
        Spare spare = new Spare(new Pins(3), new Pins(7));
        assertThat(spare.getMark()).isEqualTo("3|/");
    }
}
