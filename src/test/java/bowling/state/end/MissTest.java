package bowling.state.end;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MissTest {

    @Test
    void getPrintMarkTest() {
        assertThat(new Miss().getPrintMark()).isEqualTo("-");
    }

}