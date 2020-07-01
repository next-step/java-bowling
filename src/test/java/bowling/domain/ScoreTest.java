package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Score: Frame 당 점수를 관리")
class ScoreTest {

    @DisplayName("넘긴 총 핀수에 따라서 Score 점수가 생성된다")
    @Test
    public void getScore_WithPitch_ReturnFallenPin() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        pitch.add(new Pin(5));
        assertThat(Score.ofPitch(pitch).getScore()).isEqualTo(pitch.getFallenPin());
    }

    @DisplayName("아직 Pitch가 끝나지 않았다면 Null Score로 생성된다")
    @Test
    public void getScore_WithNotFinishPitch_ReturnNullScore() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        assertThat(Score.ofPitch(pitch)).isEqualTo(Score.ofNull());
    }
}
