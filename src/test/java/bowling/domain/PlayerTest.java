package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PlayerTest {

    @Test
    public void 사용자_이름이_3글자인_경우() {
        Player player = new Player("AAA");
        assertThat(player.getName().length()).isEqualTo(3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void 사용자_이름이_3글자가_넘는_경우() {
        Player player = new Player("AAAA");
    }
}