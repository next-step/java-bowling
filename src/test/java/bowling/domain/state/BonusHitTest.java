package bowling.domain.state;

import bowling.exception.NotSupportMethodException;
import bowling.exception.OverflowHitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusHitTest {

    private BonusHit bonusHit;

    @BeforeEach
    void setup() {
        bonusHit = new BonusHit(5);
    }

    @Test
    @DisplayName("BonusHit 상태는 종료 상태이다.")
    void isFinish() {
        assertThat(bonusHit.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 상태에서는 투구 시 예외를 반환한다.")
    void bowl() {
        assertThatThrownBy(() -> new Strike().bowl(3)).isInstanceOf(NotSupportMethodException.class);
    }

    @Test
    @DisplayName("BonusHit 의 description 은 {bonusHit} 이다")
    void description() {
        assertThat(bonusHit.description()).isEqualTo("5");
    }

    @Test
    @DisplayName("BonusHit 의 경우 보너스 찬스가 없다")
    void hasBonusChance() {
        assertThat(bonusHit.hasBonusChance()).isFalse();
    }

    @Test
    @DisplayName("BonusHit 의 bowlingCount 는 1이다.")
    void bowlingCount() {
        assertThat(bonusHit.bowlingCount()).isEqualTo(1);
    }
}