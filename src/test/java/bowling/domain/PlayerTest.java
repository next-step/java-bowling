package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @Test
    public void create() {
        assertThatCode(() -> new Player("LHG")).doesNotThrowAnyException();
    }

    @Test
    public void invalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player("LHGT"));
    }
}