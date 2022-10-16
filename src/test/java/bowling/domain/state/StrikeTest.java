package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StrikeTest {


    @Test
    @DisplayName("보너스 점수 개수는 2개")
    void bonus_two() {
        //given
        Strike strike = new Strike(new Score(10));
        //then
        assertThat(strike.bonusCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("스코어가 10이 아니면 스트라이크 생성 불가")
    void is_strike() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Strike(new Score(5)));
    }


    @Test
    @DisplayName("파이널 프레임에서 보너스 기회 있음")
    void can_get_bonus_in_final_frame() {
        //given
        Strike strike = new Strike(new Score(10));
        //then
        assertThat(strike.canGetBonus()).isTrue();
    }

    @Test
    @DisplayName("상태는 STRIKE")
    void state_is_strike() {
        //given
        Strike strike = new Strike(new Score(10));
        //then
        assertThat(strike.getBowlingState()).isEqualTo(BowlingRecordState.STRIKE);
    }

    @Test
    @DisplayName("종료된 상태")
    void is_finished() {
        //given
        Strike strike = new Strike(new Score(10));
        //then
        assertThat(strike.isFinish()).isTrue();
    }

    @Test
    @DisplayName("남은 핀 개수 0")
    void remain_pins_0() {
        //given
        Strike strike = new Strike(new Score(10));
        //then
        assertThat(strike.getRemainPins()).isZero();
    }

    @Test
    @DisplayName("볼은 더 던지먼 오류")
    void bowl_illegalStateException() {
        //given
        Strike strike = new Strike(new Score(10));
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> strike.bowl(new Score(10)));
    }

}