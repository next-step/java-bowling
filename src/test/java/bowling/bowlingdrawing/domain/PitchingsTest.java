package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.domain.pitching.Pitchings;
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
        Pitching checkPitching = Pitching.of(pins1);
        Pitching checkNextPitching = checkPitching.next(pins2);
        // then
        assertThat(pitching).isEqualTo(checkPitching);
        assertThat(nextPitching).isEqualTo(checkNextPitching);

    }

}