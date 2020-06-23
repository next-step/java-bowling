package bowling.pitch;

import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchTest {

    @DisplayName("객체 정상 생성")
    @Test
    public void makePitch_정상() {
        Score score = Score.valueOf(10);

        Pitch pitch = Pitch.initiate(score);

        assertThat(pitch.getScore()).isEqualTo(10);
    }

}
