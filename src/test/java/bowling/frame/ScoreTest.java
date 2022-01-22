package bowling.frame;

import bowling.exception.CannotScoreCalculateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    void 스트라이크_1번_점수_계산() {
        Score score = Score.ofStrike();
        assertThat(score.bowl(3).bowl(1).getScore()).isEqualTo(14);
    }

    @Test
    void 스트라이크_2번_점수_계산() {
        Score score = Score.ofStrike();
        assertThat(score.bowl(10).bowl(1).getScore()).isEqualTo(21);
    }

    @Test
    void 스트라이크_3번_점수_계산() {
        Score score = Score.ofStrike();
        assertThat(score.bowl(10).bowl(10).getScore()).isEqualTo(30);
    }

    @Test
    void 스페어_점수_계산() {
        Score score = Score.ofSpare();
        assertThat(score.bowl(1).getScore()).isEqualTo(11);
    }

    @Test
    void 미스_점수_계산() {
        Score score = Score.ofMiss(9);
        assertThat(score.getScore()).isEqualTo(9);
    }

    @Test
    void 예외_기회가_남아_있을_때_점수_계산() {
        Score score = Score.ofStrike().bowl(10);

        assertThatThrownBy(score::getScore)
                .isInstanceOf(CannotScoreCalculateException.class)
                .hasMessage("기회가 남아있어 점수를 확인할 수 없습니다.");
    }

    @Test
   void 예외_기회가_없을_때() {
        Score score = Score.ofMiss(9);

        assertThatThrownBy(() -> score.bowl(1))
                .isInstanceOf(CannotScoreCalculateException.class)
                .hasMessage("기회를 모두 소진 했습니다.");
    }
}