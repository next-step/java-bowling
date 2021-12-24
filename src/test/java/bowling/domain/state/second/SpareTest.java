package bowling.domain.state.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.state.end.Spare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpareTest {

    private Spare spare;

    @BeforeEach
    void init() {

        spare = new Spare(Pin.of(3));
    }

    @Test
    @DisplayName("Spare 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(spare.getPrintMark()).isEqualTo("/");
    }
}
