package bowling.domain.score;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchScoreTypeFactoryTest {

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 10이면 Strike를 반환")
    @Test
    public void makeScoreType_스트라이크() {
        PitchScore pitchScore = PitchScore.valueOf(10);

        ScoreType scoreType = ScoreTypeFactory.initiate(pitchScore);

        assertThat(scoreType).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 0이면 Gutter를 반환")
    @Test
    public void makeScoreType_거터() {
        PitchScore pitchScore = PitchScore.valueOf(0);

        ScoreType scoreType = ScoreTypeFactory.initiate(pitchScore);

        assertThat(scoreType).isEqualTo(ScoreType.GUTTER);
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 1~9이면 Normal을 반환")
    @Test
    public void makeScoreType_노멀() {
        PitchScore pitchScore = PitchScore.valueOf(3);

        ScoreType scoreType = ScoreTypeFactory.initiate(pitchScore);

        assertThat(scoreType).isEqualTo(ScoreType.NORMAL);
    }

    @DisplayName("next ScoreType을 만들 때 이전의 ScoreType이 Strike면 예외 발생")
    @Test
    public void next_Strike_예외() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(10));
        PitchScore pitchScore = PitchScore.valueOf(3);

        assertThatThrownBy(() -> {
            ScoreTypeFactory.next(pitch, pitchScore);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_NORMAL_PITCHES_SCORE);
    }

    @DisplayName("next ScoreType을 만들 때 이전의 ScoreType이 Spare면 예외 발생")
    @Test
    public void next_Spare_예외() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(0));
        Pitch nextPitch = pitch.next(PitchScore.valueOf(10));

        assertThatThrownBy(() -> {
            ScoreTypeFactory.next(nextPitch, PitchScore.valueOf(5));
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_NORMAL_PITCHES_SCORE);
    }

    @DisplayName("next ScoreType을 만들 때 두 Pitch의 합이 10을 넘으면 예외 발생")
    @Test
    public void nextStrike_10_초과_예외() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));
        PitchScore pitchScore = PitchScore.valueOf(9);

        assertThatThrownBy(() -> {
            ScoreTypeFactory.next(pitch, pitchScore);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.OVER_SCORE);
    }

    @DisplayName("next ScoreType을 만들 때 스페어면 / 반환")
    @Test
    public void nextStrike_스페어() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));
        PitchScore pitchScore = PitchScore.valueOf(7);

        ScoreType nextScoreType = ScoreTypeFactory.next(pitch, pitchScore);

        assertThat(nextScoreType).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("next ScoreType을 만들 때 총합이 10점이 되지 못하면 Miss반환")
    @Test
    public void nextStrike_미스() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));
        PitchScore pitchScore = PitchScore.valueOf(6);

        ScoreType nextScoreType = ScoreTypeFactory.next(pitch, pitchScore);

        assertThat(nextScoreType).isEqualTo(ScoreType.MISS);
    }
}
