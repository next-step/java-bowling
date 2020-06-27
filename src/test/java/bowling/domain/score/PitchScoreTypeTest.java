package bowling.domain.score;

import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchScoreTypeTest {

    @DisplayName("Strike는 X를 반환")
    @Test
    public void strike_X() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(10));

        assertThat(ScoreType.STRIKE.getSignature(pitch)).isEqualTo("X");
    }

    @DisplayName("Spare는 /를 반환")
    @Test
    public void spare_slash() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(8));

        assertThat(ScoreType.SPARE.getSignature(pitch)).isEqualTo("/");
    }

    @DisplayName("Gutter는 -를 반환")
    @Test
    public void gutter_dash() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(0));

        assertThat(ScoreType.GUTTER.getSignature(pitch)).isEqualTo("-");
    }

    @DisplayName("Miss는 숫자를 반환")
    @Test
    public void miss_숫자() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));

        assertThat(ScoreType.MISS.getSignature(pitch)).isEqualTo("3");
    }

    @DisplayName("Normal은 숫자를 반환")
    @Test
    public void normal_숫자() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));

        assertThat(ScoreType.NORMAL.getSignature(pitch)).isEqualTo("3");
    }
}
