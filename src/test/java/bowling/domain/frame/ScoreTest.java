package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.exception.CannotScoreCalculateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    void 예외_미스_점수_10_초과() {
        assertThatThrownBy(() -> Score.ofMiss(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("미스 상태의 점수는 %d이상일 수 없습니다.", Pins.MAX_RANGE));
    }

//    @Test
//    void 예외_남은_기회가_0미만이면_굴릴_수_없다() {
//        Score score = Score.ofMiss(9);
//        assertThatThrownBy(() -> score.bowl(1))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("남은 기회가 없어 이번 프레임에서는 굴릴 수 없습니다.");
//    }

    @Test
    void 예외_남은_기회가_있으면_점수를_가져올_수_없다() {
        Score score = Score.ofStrike();
        assertThatThrownBy(score::getScore)
                .isInstanceOf(CannotScoreCalculateException.class)
                .hasMessage("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
    }

    @Test
    void 스트라이크_2번() {
        Score score = Score.ofStrike();
        assertThat(score.bowl(10).bowl(9).getScore()).isEqualTo(29);
    }

    @Test
    void 스트라이크_1번() {
        Score score = Score.ofStrike();
        assertThat(score.bowl(9).bowl(1).getScore()).isEqualTo(20);
    }

    @Test
    void 스페어() {
        Score score = Score.ofSpare();
        assertThat(score.bowl(10).getScore()).isEqualTo(20);
    }
}