package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameStateTest {

    @Test
    void from() {
        assertThat(FrameState.from(1)).isNotNull();
    }

    @Test
    void store() {
        FrameState frameState = FrameState.from(1);
        assertThat(frameState.store("")).isEqualTo("");
        assertThat(frameState.store("")).isEqualTo("|");
    }

    @Test
    void getNumber() {
        assertThat(FrameState.from(1).getNumber()).isEqualTo(1);
    }
}
