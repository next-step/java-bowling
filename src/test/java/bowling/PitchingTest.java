package bowling;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PitchingTest {
    @Test
    public void getPitchingTest() {
        int knockDownPins = 10;
        assertThat(Pitching.getPitching(knockDownPins)).isEqualTo(Pitching.STRIKE);
    }
}
