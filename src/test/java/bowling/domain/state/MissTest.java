package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {
    @DisplayName("Miss 상태 생성")
    @Test
    void create() {
        Miss miss = new Miss(1, 5);

        assertThat(miss).hasToString("1|5");
    }

    @DisplayName("Miss 유효하지 않은 생성")
    @Test
    void create_invalid() {
        assertThatThrownBy(() -> new Miss(5, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Miss 상태에서 투구시 exception 발생")
    @Test
    void pitch() {
        Strike strike = new Strike();
        assertThatThrownBy(() -> strike.pitch(10))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
