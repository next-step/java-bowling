package bowling.domain.score;

import bowling.domain.exception.NextPitchScoreTypeException;
import bowling.domain.exception.PitchScoreSumMaximumException;
import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchScoreTypeTest {

    @DisplayName("Strike는 X를 반환")
    @Test
    public void strike_X() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(10));

        assertThat(PitchScoreType.STRIKE.getPitchScoreSignature(pitch)).isEqualTo("X");
    }

    @DisplayName("Spare는 /를 반환")
    @Test
    public void spare_slash() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(8));

        assertThat(PitchScoreType.SPARE.getPitchScoreSignature(pitch)).isEqualTo("/");
    }

    @DisplayName("Gutter는 -를 반환")
    @Test
    public void gutter_dash() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(0));

        assertThat(PitchScoreType.GUTTER.getPitchScoreSignature(pitch)).isEqualTo("-");
    }

    @DisplayName("Miss는 숫자를 반환")
    @Test
    public void miss_숫자() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));

        assertThat(PitchScoreType.MISS.getPitchScoreSignature(pitch)).isEqualTo("3");
    }

    @DisplayName("Normal은 숫자를 반환")
    @Test
    public void normal_숫자() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));

        assertThat(PitchScoreType.NORMAL.getPitchScoreSignature(pitch)).isEqualTo("3");
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 10이면 Strike를 반환")
    @Test
    public void makeScoreType_스트라이크() {
        PitchScore pitchScore = PitchScore.valueOf(10);

        PitchScoreType pitchScoreType = PitchScoreType.initiate(pitchScore);

        assertThat(pitchScoreType).isEqualTo(PitchScoreType.STRIKE);
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 0이면 Gutter를 반환")
    @Test
    public void makeScoreType_거터() {
        PitchScore pitchScore = PitchScore.valueOf(0);

        PitchScoreType pitchScoreType = PitchScoreType.initiate(pitchScore);

        assertThat(pitchScoreType).isEqualTo(PitchScoreType.GUTTER);
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 1~9이면 Normal을 반환")
    @Test
    public void makeScoreType_노멀() {
        PitchScore pitchScore = PitchScore.valueOf(3);

        PitchScoreType pitchScoreType = PitchScoreType.initiate(pitchScore);

        assertThat(pitchScoreType).isEqualTo(PitchScoreType.NORMAL);
    }

    @DisplayName("next ScoreType을 만들 때 이전의 ScoreType이 Strike면 예외 발생")
    @Test
    public void next_Strike_예외() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(10));
        PitchScore pitchScore = PitchScore.valueOf(3);

        assertThatThrownBy(() -> {
            PitchScoreType.next(pitch, pitchScore);
        }).isInstanceOf(NextPitchScoreTypeException.class);
    }

    @DisplayName("next ScoreType을 만들 때 이전의 ScoreType이 Spare면 예외 발생")
    @Test
    public void next_Spare_예외() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(0));
        Pitch nextPitch = pitch.next(PitchScore.valueOf(10));

        assertThatThrownBy(() -> {
            PitchScoreType.next(nextPitch, PitchScore.valueOf(5));
        }).isInstanceOf(NextPitchScoreTypeException.class);
    }

    @DisplayName("next ScoreType을 만들 때 두 Pitch의 합이 10을 넘으면 예외 발생")
    @Test
    public void next_점수합_10_초과_예외() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));
        PitchScore pitchScore = PitchScore.valueOf(9);

        assertThatThrownBy(() -> {
            PitchScoreType.next(pitch, pitchScore);
        }).isInstanceOf(PitchScoreSumMaximumException.class);
    }

    @DisplayName("next ScoreType을 만들 때 점수의 합이 10이면 스페어 반환")
    @Test
    public void next_점수합_10_스페어() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));
        PitchScore pitchScore = PitchScore.valueOf(7);

        PitchScoreType nextPitchScoreType = PitchScoreType.next(pitch, pitchScore);

        assertThat(nextPitchScoreType).isEqualTo(PitchScoreType.SPARE);
    }

    @DisplayName("next ScoreType을 만들 때 총합이 10점이 되지 못하면 Miss반환")
    @Test
    public void next_미스() {
        Pitch pitch = Pitch.initiate(PitchScore.valueOf(3));
        PitchScore pitchScore = PitchScore.valueOf(6);

        PitchScoreType nextPitchScoreType = PitchScoreType.next(pitch, pitchScore);

        assertThat(nextPitchScoreType).isEqualTo(PitchScoreType.MISS);
    }
}
