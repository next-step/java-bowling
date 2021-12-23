package bowling.domain.state.progress.end;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.end.Strike;
import org.junit.jupiter.api.Test;

public class StrikeTest {

    @Test
    void getPrintMarkTest() {
        assertThat(new Strike().getPrintMark()).isEqualTo("X");
    }
}
