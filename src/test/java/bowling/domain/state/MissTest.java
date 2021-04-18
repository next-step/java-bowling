package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {

    @Test
    @DisplayName("Miss 상태 확인")
    void create() {
        Miss miss = new Miss(3, 4);
        assertThat(miss).hasToString("3|4");
    }

    @Test
    @DisplayName("Miss 유효성 확인")
    void valid() {
        assertThatThrownBy(() -> new Miss(6, 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
