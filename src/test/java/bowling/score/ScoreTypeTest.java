package bowling.score;

import bowling.pitch.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {

    @DisplayName("Strike는 X를 반환")
    @Test
    public void strike_X() {
        Pitch pitch = Pitch.initiate(Score.valueOf(10));

        assertThat(ScoreType.STRIKE.getSignature(pitch)).isEqualTo("X");
    }

    @DisplayName("Spare는 /를 반환")
    @Test
    public void spare_slash() {
        Pitch pitch = Pitch.initiate(Score.valueOf(8));

        assertThat(ScoreType.SPARE.getSignature(pitch)).isEqualTo("/");
    }

    @DisplayName("Gutter는 -를 반환")
    @Test
    public void gutter_dash() {
        Pitch pitch = Pitch.initiate(Score.valueOf(0));

        assertThat(ScoreType.GUTTER.getSignature(pitch)).isEqualTo("-");
    }

    @DisplayName("Miss는 숫자를 반환")
    @Test
    public void miss_숫자() {
        Pitch pitch = Pitch.initiate(Score.valueOf(3));

        assertThat(ScoreType.MISS.getSignature(pitch)).isEqualTo("3");
    }
}
