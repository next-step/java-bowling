package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PitchingTest {
    @Test
    void isStrike() {
        Pitching pitching = new Pitching(10);
        assertThat(pitching.isStrike()).isTrue();
    }
}
