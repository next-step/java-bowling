package bowling.player;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @Test
    public void 이름이_3글자가_아니면_IllegalArgumentException() {
        // given
        String name = "YC";

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy(() -> new Player(name));
    }

    @Test
    public void 이름이_영문이_아니면_IllegalArgumentException() {
        // given
        String name = "손영철";

        // when
        // then
        assertThatIllegalArgumentException().isThrownBy(() -> new Player(name));
    }
}
