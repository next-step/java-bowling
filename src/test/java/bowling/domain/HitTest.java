package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {
    @Test
    void returnRemainingPin() {
        Hit hit = new Hit(1 );
        assertThat(hit.remainingPin()).isEqualTo(9);
    }
}
