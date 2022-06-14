package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;
import bowling.exception.NotCreateStateException;
import bowling.exception.NotSupportMethodException;
import bowling.exception.OverflowHitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    private Spare spare;

    @BeforeEach
    void setup() {
        spare = new Spare(Hit.valueOf(3), Hit.valueOf(7));
    }

    @Test
    @DisplayName("Spare 상태는 종료 상태이다.")
    void isFinish() {
        assertThat(spare.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스페어 생성 때 두번의 Hit 의 총합이 10이 아닌 경우 예외를 반환한다.")
    void invalidSpare() {
        assertThatThrownBy(() -> new Spare(Hit.valueOf(3), Hit.valueOf(2)))
                .isInstanceOf(NotCreateStateException.class)
                .hasMessage("Hit 수가 적절하지 않습니다. Hit: 5");

        assertThatThrownBy(() -> new Spare(Hit.valueOf(9), Hit.valueOf(2)))
                .isInstanceOf(OverflowHitException.class)
                .hasMessage("10 개를 넘길 수 없습니다. 입력한 Hit 수: 9, 2");
    }

    @Test
    @DisplayName("스페어 상태에서 투구 시 예외를 반환한다.")
    void bowl() {
        assertThatThrownBy(() -> spare.bowl(3))
                .isInstanceOf(NotSupportMethodException.class);
    }

    @Test
    @DisplayName("Spare 의 description 은 {firstHit}|/ 이다")
    void description() {
        assertThat(spare.description()).isEqualTo("3|/");
    }

    @Test
    @DisplayName("Spare 의 경우 보너스 찬스가 있다")
    void hasBonusChance() {
        assertThat(spare.hasBonusChance()).isTrue();
    }

    @Test
    @DisplayName("Spare 의 bowlingCount 는 2이다.")
    void bowlingCount() {
        assertThat(spare.bowlingCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("Spare 의 점수는 Spare Score 를 반환한다.")
    void score() {
        assertThat(spare.score()).isEqualTo(Score.spare());
    }

    @Test
    @DisplayName("Spare 의 경우 추가계산이 1회 필요한 점수에 {firstHit} 점을 추가한다.")
    void calculateAdditionalScore_1() {
        Score score = Score.of(10, 1);

        Score calculatedScore = spare.calculateAdditionalScore(score);

        assertThat(calculatedScore).isEqualTo(Score.of(13, 0));
    }

    @Test
    @DisplayName("Spare 의 경우 추가계산이 2회 필요한 점수에 {firstHit} + {secondHit} 점을 추가한다.")
    void calculateAdditionalScore_2() {
        Score score = Score.of(10, 2);

        Score calculatedScore = spare.calculateAdditionalScore(score);

        assertThat(calculatedScore).isEqualTo(Score.of(20, 0));
    }

    @Test
    @DisplayName("Spare 의 경우 진행 중 상태이 아니다.")
    void isProgressing() {
        assertThat(spare.isProgressing()).isFalse();
    }
}