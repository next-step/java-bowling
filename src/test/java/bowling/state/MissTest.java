package bowling.state;

import bowling.domain.state.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {

    @Test
    @DisplayName("Miss 상태")
    void testMiss() {
        Miss miss = new Miss(1,8);
        assertThat(miss).hasToString("1|8");
    }

    @Test
    @DisplayName("Miss valid")
    void testValid() {
        Miss miss = new Miss(2,6);
        assertThatThrownBy(() -> miss.bowl(2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
