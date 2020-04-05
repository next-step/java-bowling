package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ScoreTest {

    @Test
    @DisplayName("스코어 객체 생성")
    void create() {
        Score spare = Score.ofSpare();
        Score expectedSpare = Score.ofSpare();

        boolean same = spare.equals(expectedSpare);

        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("스코어 계산 예외처리")
    void exceptCalculation() {
        // give
        Score strike = Score.ofStrike();
        strike.bowl(3);
        // when then
        assertThatIllegalArgumentException().isThrownBy(strike::getScore);
    }

    @Test
    @DisplayName("미스 - 스코어 계산 값 가져오기")
    void calculatedByMiss() {
        // give
        Score miss = Score.ofMiss(7);
        // when
        int actualScore = miss.getScore();
        // then
        assertThat(actualScore).isEqualTo(7);
    }

    @Test
    @DisplayName("스페어 - 스코어 계산 값 가져오기")
    void calculatedBySpare() {
        // give
        Score spare = Score.ofSpare();
        // when
        int actualScore = spare.bowl(3).getScore();
        // then
        assertThat(actualScore).isEqualTo(13);
    }

    @Test
    @DisplayName("스트라이크 - 스코어 계산 값 가져오기")
    void calculatedByStrike() {
        // give
        Score spare = Score.ofStrike();
        // when
        int actualScore = spare.bowl(3).bowl(6).getScore();
        // then
        assertThat(actualScore).isEqualTo(19);
    }
}
