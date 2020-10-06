package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlTest {
    @Test
    void pitch() {
        FirstBowl firstBowl = new FirstBowl(1);
        assertThat(firstBowl).hasToString("1");
    }
}
