package bowling.state.end;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SpareTest {

    @Test
    void getPrintMarkTest() {
        assertThat(new Spare().getPrintMark()).isEqualTo("/");
    }
}
