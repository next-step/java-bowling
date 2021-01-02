package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Pitch;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MissTest {

    @Test
    @DisplayName("미스 점수 가져오기")
    void getScore() {
        Pitch pitch1 = Pitch.from(2);
        Pitch pitch2 = Pitch.from(3);
        Miss miss = new Miss(pitch1, pitch2);

        assertThat(miss.getScore()).isEqualTo(Score.of(5, 0));
    }

    @Test
    @DisplayName("추가 점수 계산하기 - Strike")
    void calculateAdditionalStrikeScore() {
        Pitch pitch1 = Pitch.from(2);
        Pitch pitch2 = Pitch.from(3);
        Miss miss = new Miss(pitch1, pitch2);

        Score score = Score.ofStrike();
        Score newScore = miss.calculateAdditionalScore(score);

        assertThat(newScore.getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("추가 점수 계산하기 - Spare")
    void calculateAdditionalSpareScore() {
        Pitch pitch1 = Pitch.from(2);
        Pitch pitch2 = Pitch.from(3);
        Miss miss = new Miss(pitch1, pitch2);

        Score score = Score.ofSpare();
        Score newScore = miss.calculateAdditionalScore(score);

        assertThat(newScore.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("스페어 투구 예외 처리")
    void bowlException() {
        Pitch pitch1 = Pitch.from(2);
        Pitch pitch2 = Pitch.from(3);
        Miss miss = new Miss(pitch1, pitch2);

        assertThrows(IllegalArgumentException.class,
                () -> miss.bowl(1));
    }

    @Test
    @DisplayName("스페어 종료 상태")
    void isFinished() {
        Pitch pitch1 = Pitch.from(2);
        Pitch pitch2 = Pitch.from(3);
        Miss miss = new Miss(pitch1, pitch2);

        assertThat(miss.isFinish()).isTrue();
    }

}