package bowling.domain.state.end;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StrikeTest {

    @Test
    @DisplayName("Strike는 10개의 핀이 와야해요.")
    void strikeValidTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Strike(Pin.of(5)));
    }

    @Test
    void getPrintMarkTest() {
        assertThat(new Strike(Pin.of(10)).getPrintMark()).isEqualTo("X");
    }
}
