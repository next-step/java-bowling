package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MissTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new Miss(new Pin(0), new Pin(0)).isFinished()).isTrue();
    }

    @Test
    void 게임진행_예외발생() {
        assertThatThrownBy(() -> new Miss(new Pin(0), new Pin(0)).bowl(new Pin(0)))
                .isInstanceOf(IllegalStateException.class);
    }
}
