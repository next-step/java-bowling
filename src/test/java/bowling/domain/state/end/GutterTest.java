package bowling.domain.state.end;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import bowling.domain.state.end.first.Gutter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GutterTest {


    @Test
    @DisplayName("Gutter은 0개의 핀이 와야해요.")
    void strikeValidTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Gutter(Pin.of(5)));
    }

    @Test
    void getPrintMarkTest() {
        assertThat(new Gutter(Pin.of(0)).getPrintMark()).isEqualTo("-");
    }
}