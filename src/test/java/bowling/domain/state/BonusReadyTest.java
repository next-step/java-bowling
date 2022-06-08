package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BonusReadyTest {

    @Test
    @DisplayName("10개를 히트하면 스트라이크 상태를 반환한다.")
    void hit_10() {
        assertThat(new BonusReady().bowl(10)).isInstanceOf(Strike.class);
    }

    @ParameterizedTest(name = "{0}개를 히트하면 BonusHit 상태를 반환한다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void hit_less_10(int hit) {
        assertThat(new BonusReady().bowl(hit)).isEqualTo(new BonusHit(hit));
    }

    @Test
    @DisplayName("BonusReady 상태는 활성화 상태이다.")
    void name() {
        assertThat(new BonusReady().isFinish()).isFalse();
    }

    @Test
    @DisplayName("BonusReady 의 description 은 공백이다")
    void description() {
        assertThat(new BonusReady().description()).isEqualTo("");
    }

    @Test
    @DisplayName("BonusReady 의 경우 보너스 찬스가 없다")
    void hasBonusChance() {
        assertThat(new BonusReady().hasBonusChance()).isFalse();
    }

    @Test
    @DisplayName("Ready 의 bowlingCount 는 0이다.")
    void bowlingCount() {
        assertThat(new BonusReady().bowlingCount()).isEqualTo(0);
    }
}