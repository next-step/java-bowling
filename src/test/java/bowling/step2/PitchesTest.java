package bowling.step2;

import bowling.step2.domain.pitch.PitchType;
import bowling.step2.domain.pitch.Pitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchesTest {

    @Test
    @DisplayName("투구 기록 테스트 ")
    void addPitchTest() {
        Pitches pitches = new Pitches();
        pitches.addPitch(3);
        assertThat(pitches.size()).isEqualTo(1);
        pitches.addPitch(5);
        assertThat(pitches.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구에 따른 타입(표기) 테스트 - 스트라이크")
    void pitchTypeTest_strike() {
        Pitches pitches = new Pitches();
        pitches.addPitch(10);
        assertThat(pitches.getPitches().get(0).getPitch()).isEqualTo(10);
        assertThat(pitches.getPitches().get(0).getPitchType()).isEqualTo(PitchType.STRIKE);

        pitches.addFinalPitch(10);
        assertThat(pitches.getPitches().get(1).getPitch()).isEqualTo(10);
        assertThat(pitches.getPitches().get(1).getPitchType()).isEqualTo(PitchType.STRIKE);

    }

    @Test
    @DisplayName("투구에 따른 타입(표기) 테스트 - 스페어")
    void pitchTypeTest_spare() {
        Pitches pitches = new Pitches();
        pitches.addPitch(8);
        pitches.addPitch(2);
        assertThat(pitches.getPitches().get(0).getPitch()).isEqualTo(8);
        assertThat(pitches.getPitches().get(0).getPitchType()).isEqualTo(PitchType.DEFAULT);
        assertThat(pitches.getPitches().get(1).getPitch()).isEqualTo(2);
        assertThat(pitches.getPitches().get(1).getPitchType()).isEqualTo(PitchType.SPARE);

        pitches.addFinalPitch(6);
        pitches.addFinalPitch(4);
        assertThat(pitches.getPitches().get(2).getPitch()).isEqualTo(6);
        assertThat(pitches.getPitches().get(2).getPitchType()).isEqualTo(PitchType.DEFAULT);
        assertThat(pitches.getPitches().get(3).getPitch()).isEqualTo(4);
        assertThat(pitches.getPitches().get(3).getPitchType()).isEqualTo(PitchType.SPARE);
    }

    @Test
    @DisplayName("투구에 따른 타입(표기) 테스트 - 거터")
    void pitchTypeTest_gutter() {
        Pitches pitches = new Pitches();
        pitches.addPitch(0);
        pitches.addPitch(0);
        assertThat(pitches.getPitches().get(0).getPitch()).isEqualTo(0);
        assertThat(pitches.getPitches().get(0).getPitchType()).isEqualTo(PitchType.GUTTER);
        assertThat(pitches.getPitches().get(1).getPitch()).isEqualTo(0);
        assertThat(pitches.getPitches().get(1).getPitchType()).isEqualTo(PitchType.GUTTER);
    }
}