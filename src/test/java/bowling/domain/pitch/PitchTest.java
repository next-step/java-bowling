package bowling.domain.pitch;

import bowling.domain.score.Score;
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
        assertThat(pitch.getScoreSignature()).isEqualTo("X");
    }

    @DisplayName("기존 Pitch 객체로 스페어를 지닌 다음 객체를 생성")
    @Test
    public void next_스페어() {
        Score score = Score.valueOf(5);
        Pitch lastPitch = Pitch.initiate(score);

        Pitch nextPitch = lastPitch.next(score);

        assertThat(nextPitch.getScoreSignature()).isEqualTo("/");
    }
}
