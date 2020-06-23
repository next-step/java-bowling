package bowling.pitch;

import bowling.score.Score;
import bowling.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalPitchesTest {

    @DisplayName("첫 투구면 Pitch를 initiate하여 내부 컬렉션에 추가함")
    @Test
    public void throwBall_첫_투구() {
        Score score = Score.valueOf(5);
        NormalPitches normalPitches = new NormalPitches();
        List<Pitch> normalPitchesList = normalPitches.getPitches();

        normalPitches.throwBall(score);
        Pitch currentPitch = normalPitchesList.get(0);

        assertThat(currentPitch.getScore()).isEqualTo(5);
        assertThat(currentPitch.getScoreType()).isEqualTo(ScoreType.NORMAL);
    }
}
