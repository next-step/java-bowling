package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @DisplayName("Strike viewString()은 X를 반환한다.")
    @Test
    void viewStringTest() {
        assertThat(new Strike().viewString()).isEqualTo("X");
    }

}
