package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;
import bowling.exception.NotCreateStateException;
import bowling.exception.OverflowHitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    private Miss miss;

    @BeforeEach
    void setup() {
        miss = new Miss(Hit.valueOf(0), Hit.valueOf(5));
    }

    @Test
    @DisplayName("Miss 생성 때 두번의 Hit 의 총합이 10이 아닌 경우 예외를 반환한다.")
    void invalidMiss() {
        assertThatThrownBy(() -> new Miss(Hit.valueOf(5), Hit.valueOf(5)))
                .isInstanceOf(NotCreateStateException.class)
                .hasMessage("Hit 수가 적절하지 않습니다. Hit: 10");

        assertThatThrownBy(() -> new Spare(Hit.valueOf(9), Hit.valueOf(2)))
                .isInstanceOf(OverflowHitException.class)
                .hasMessage("10 개를 넘길 수 없습니다. 입력한 Hit 수: 9, 2");
    }

    @Test
    @DisplayName("Miss 상태는 종료 상태이다.")
    void isFinish() {
        assertThat(miss.isFinish()).isTrue();
    }

    @Test
    @DisplayName("Miss 의 description 은 {firstHit}|{secondHit} 이다")
    void description() {
        assertThat(miss.description()).isEqualTo("-|5");
    }

    @Test
    @DisplayName("Miss 의 경우 보너스 찬스가 없다")
    void hasBonusChance() {
        assertThat(miss.hasBonusChance()).isFalse();
    }

    @Test
    @DisplayName("Miss 의 bowlingCount 는 2이다.")
    void bowlingCount() {
        assertThat(miss.bowlingCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("Miss 의 점수는 Miss Score 를 반환한다.")
    void score() {
        assertThat(miss.score()).isEqualTo(Score.of(5, 0));
    }
}