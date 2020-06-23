package bowling.pitch;

import bowling.score.Score;
import bowling.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchTest {

    @DisplayName("첫 번째 Pitch 생성")
    @Test
    public void initiatePitch_정상() {
        Score score = Score.valueOf(10);

        Pitch pitch = Pitch.initiate(score);

        assertThat(pitch.getScore()).isEqualTo(10);
        assertThat(pitch.getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("기존 Pitch 객체로 다음 Pitch 객체 생성")
    @Test
    public void next() {

    }
}
