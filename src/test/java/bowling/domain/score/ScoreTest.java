package bowling.domain.score;

import bowling.domain.frame.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    @DisplayName("10 Pitch 를 기록하면 10점에 STRIKE score 가 된다.")
    void strikeScore() {
        Pitch strike = Pitch.from(10);
        Score score = Score.from(strike);

        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(10),
                () -> assertThat(score.isStrike()).isTrue()
        );
    }

    @Test
    @DisplayName("일반 Pitch 를 기록하면 PROCESS 가 된다.")
     void normalPitch() {
        Pitch pitch = Pitch.from(3);
        Score score = Score.from(pitch);

        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(3),
                () -> assertThat(score.isProgress()).isTrue()
        );
    }

    @Test
    @DisplayName("PROGRESS 에 pitch 를 한번더 기록하면 DONE 이 된다.")
    void progress_state_add_pitch_then_done() {
        Pitch pitch = Pitch.from(3);
        Score score = Score.from(pitch);
        score.addScore(pitch);

        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(6),
                () -> assertThat(score.isDone()).isTrue()
        );
    }

    @Test
    @DisplayName("두 pitch 의 합이 10 이라면 SPARE 가 된다.")
    void spare() {
        Pitch pitch = Pitch.from(5);
        Score score = Score.from(pitch);
        score.addScore(pitch);

        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(10),
                () -> assertThat(score.isSpare()).isTrue()
        );
    }

    @Test
    @DisplayName("STRIKE score 가 보너스 점수 계산이 된다면, 점수가 더해지고 DONE 이 된다.")
    void strike_score_add_bonus_then_done() {
        Pitch pitch = Pitch.from(10);
        Score score = Score.from(pitch);

        Score bonusScore = Score.from(pitch);
        score.addStrikeBonusScore(bonusScore);

        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(20),
                () -> assertThat(score.isDone()).isTrue()
        );
    }

    @Test
    @DisplayName("SPARE score 가 보너스 점수 계산이 된다면, 점수가 더해지고 DONE 이 된다.")
    void spare_score_add_bonus_then_done() {
        Pitch pitch = Pitch.from(5);
        Score score = Score.from(pitch);
        score.addScore(pitch);

        score.addSpareBonusScore(7);
        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(17),
                () -> assertThat(score.isDone()).isTrue()
        );
    }
}