package bowling.domain.state.second.end;

import static org.assertj.core.api.Assertions.*;

import bowling.domain.state.end.first.Gutter;
import org.junit.jupiter.api.Test;

class GutterTest {

    @Test
    void getPrintMarkTest() {
        assertThat(new Gutter().getPrintMark()).isEqualTo("-");
    }
}