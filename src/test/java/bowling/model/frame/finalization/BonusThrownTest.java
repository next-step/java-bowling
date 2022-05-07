package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("보너스 던진 상태")
class BonusThrownTest {

    private static final BonusHit MAX_BONUS_HIT_FROM_STRIKE = BonusHit.of(Strike.instance(), Pins.MAX);

    @Test
    @DisplayName("보너스 점수와 남은 개수로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> BonusThrown.of(MAX_BONUS_HIT_FROM_STRIKE, 1));
    }

    @Test
    @DisplayName("보너스 맞춘 정보는 필수")
    void instance_nullBonusHit_throwIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> BonusThrown.of(null, 1));
    }

    @Test
    @DisplayName("남은 개수는 반드시 0 이상")
    void instance_negativeCount_throwIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> BonusThrown.of(MAX_BONUS_HIT_FROM_STRIKE, -1));
    }

    @ParameterizedTest(name = "[{index}] 남은 갯수가 {0} 이라면 종료 여부는 {1}")
    @DisplayName("종료 여부")
    @CsvSource({"1,false", "0,true"})
    void isEnd(int restCount, boolean expected) {
        assertThat(BonusThrown.of(MAX_BONUS_HIT_FROM_STRIKE, restCount).isEnd()).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 상태")
    void state() {
        //given
        BonusThrown bonusThrown = BonusThrown.of(MAX_BONUS_HIT_FROM_STRIKE, 1);
        //when, then
        assertThat(bonusThrown.state(Pins.MAX)).isEqualTo(BonusThrown.of(BonusHit.of(bonusThrown, Pins.MAX), 0));
    }

    @Test
    @DisplayName("남은 갯수가 0인 상태에서 다음 상태 생성 불가")
    void state_zeroCount_thrownIllegalStateArgument() {
        assertThatIllegalStateException().isThrownBy(() -> BonusThrown.of(MAX_BONUS_HIT_FROM_STRIKE, 0).state(Pins.MAX));
    }
}
