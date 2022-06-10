package bowling.domain.state;

import bowling.exception.NotSupportMethodException;
import bowling.exception.OverflowHitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstHitTest {

    private FirstHit firstHit;

    @BeforeEach
    void setup() {
        firstHit = new FirstHit(5);
    }

    @Test
    @DisplayName("FirstHit 상태는 활성화 상태이다.")
    void isFinish() {
        assertThat(firstHit.isFinish()).isFalse();
    }

    @Test
    @DisplayName("첫번째 투구와 두번째 투구의 합이 10이면 Spare 를 반환한다.")
    void bowl_spare() {
        assertThat(firstHit.bowl(5)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("첫번째 투구와 두번째 투구의 합이 10보다 작으면 Miss 를 반환한다.")
    void bowl_miss() {
        assertThat(firstHit.bowl(2)).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("첫번째 투구와 두번째 투구의 합이 10보다 크면 예외를 반환한다.")
    void invalidHit() {
        assertThatThrownBy(() -> firstHit.bowl(7))
                .isInstanceOf(OverflowHitException.class)
                .hasMessage("10 개를 넘길 수 없습니다. 입력한 Hit 수: 5, 7");
    }

    @Test
    @DisplayName("FirstHit 의 description 은 {firstHit} 이다")
    void description() {
        assertThat(firstHit.description()).isEqualTo("5");
    }

    @Test
    @DisplayName("FirstHit 의 경우 보너스 찬스가 없다")
    void hasBonusChance() {
        assertThat(firstHit.hasBonusChance()).isFalse();
    }

    @Test
    @DisplayName("FirstHit 의 bowlingCount 는 1이다.")
    void bowlingCount() {
        assertThat(firstHit.bowlingCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("FirstHit 의 score 는 프레임이 종료되지 않아 예외를 반환한다.")
    void score() {
        assertThatThrownBy(() -> firstHit.score()).isInstanceOf(NotSupportMethodException.class);
    }
}