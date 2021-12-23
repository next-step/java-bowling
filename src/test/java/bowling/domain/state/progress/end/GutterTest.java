package bowling.domain.state.progress.end;

import static org.assertj.core.api.Assertions.*;

import bowling.domain.state.end.Gutter;
import org.junit.jupiter.api.Test;

class GutterTest {

    @Test
    void getPrintMarkTest() {
        assertThat(new Gutter().getPrintMark()).isEqualTo("-");
    }
}