package bowling.bowlingdrawing.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

class PitchingsTest {

    @Test
    @DisplayName("nextPitching 반환")
    void nextPitching() {
        // given
        Pitchings pitchings = new Pitchings();
        int pins1 = 9;
        int pins2 = 10;
        // when
        Pitching pitching = pitchings.nextPitching(pins1);
        Pitching nextPitching = pitchings.nextPitching(pins2);
        // then
        assertThat(pitching).isEqualTo(Pitching.of(pins1));
        assertThat(nextPitching).isEqualTo(pitching.next(pins2));
    }

}