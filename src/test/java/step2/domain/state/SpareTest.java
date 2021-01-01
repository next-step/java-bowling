package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Pitch;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpareTest {

    @Test
    @DisplayName("스페어 점수 가져오기")
    void getScore() {
        Pitch pitch = Pitch.from(7);
        FirstBowl firstBowl = new FirstBowl(pitch);
        State state = firstBowl.bowl(3);

        assertThat(state.getScore()).isEqualTo(Score.of(10, 1));
    }

    @Test
    @DisplayName("추가 점수 계산하기 - Strike")
    void calculateAdditionalStrikeScore() {
        Pitch pitch = Pitch.from(7);
        FirstBowl firstBowl = new FirstBowl(pitch);
        State state = firstBowl.bowl(3);

        Score score = Score.ofStrike();
        Score newScore = state.calculateAdditionalScore(score);

        assertThat(newScore.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("추가 점수 계산하기 - Spare")
    void calculateAdditionalSpareScore() {
        Pitch pitch = Pitch.from(7);
        FirstBowl firstBowl = new FirstBowl(pitch);
        State state = firstBowl.bowl(3);

        Score score = Score.ofSpare();
        Score newScore = state.calculateAdditionalScore(score);

        assertThat(newScore.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("스페어 투구 예외 처리")
    void bowlException() {
        Pitch pitch = Pitch.from(7);
        FirstBowl firstBowl = new FirstBowl(pitch);
        State state = firstBowl.bowl(3);

        assertThrows(IllegalArgumentException.class,
                () -> state.bowl(1));
    }

    @Test
    @DisplayName("스페어 종료 상태")
    void isFinished() {
        Pitch pitch = Pitch.from(7);
        FirstBowl firstBowl = new FirstBowl(pitch);
        State state = firstBowl.bowl(3);

        assertThat(state.isFinish()).isTrue();
    }

}