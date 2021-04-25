package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchesTest {

    @Test
    @DisplayName("핀 처리횟수 계산 - 투구실행을 하지 않은 경우")
    void pinDownCount_empty() {
        // given
        Pitches pitches = new Pitches();

        // when then
        assertThat(0).isEqualTo(pitches.pinDownCount());
    }

    @Test
    @DisplayName("핀 처리횟수 계산 - 투구를 실행한 경우")
    void pinDownCount() {
        // given
        Pitches pitches = new Pitches();
        pitches.add(new Pitch(1));
        pitches.add(new Pitch(9));

        // when then
        assertThat(10).isEqualTo(pitches.pinDownCount());
    }

    @Test
    @DisplayName("투구횟수 계산")
    void count() {
        // given
        Pitches pitchesNonPitch = new Pitches();
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(1));
        pitches.add(new Pitch(9));

        // then
        assertThat(0).isEqualTo(pitchesNonPitch.count());
        assertThat(2).isEqualTo(pitches.count());
    }
}