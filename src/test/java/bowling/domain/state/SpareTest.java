package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SpareTest {

    @Test
    @DisplayName("보너스 점수 개수는 1개")
    void bonus_one() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(6));
        Spare spare = new Spare(scores);
        //then
        assertThat(spare.bonusCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("스코어가 스페어가 스페어 생성 불가")
    void is_strike() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(5));
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Spare(scores));
    }

    @Test
    @DisplayName("파이널 프레임에서 보너스 기회 있음")
    void can_get_bonus_in_final_frame() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(6));
        Spare spare = new Spare(scores);
        //then
        assertThat(spare.canGetBonus()).isTrue();
    }

    @Test
    @DisplayName("상태는 SPARE")
    void state_is_spare() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(6));
        Spare spare = new Spare(scores);
        //then
        assertThat(spare.getBowlingState()).isEqualTo(BowlingRecordState.SPARE);
    }

    @Test
    @DisplayName("종료된 상태")
    void is_finished() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(6));
        Spare spare = new Spare(scores);
        //then
        assertThat(spare.isFinish()).isTrue();
    }

    @Test
    @DisplayName("남은 핀 개수 0")
    void remain_pins_0() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(6));
        Spare spare = new Spare(scores);
        //then
        assertThat(spare.getRemainPins()).isZero();
    }

    @Test
    @DisplayName("볼은 더 던지먼 오류")
    void bowl_illegalStateException() {
        //given
        Scores scores = Scores.of(new Score(4));
        scores.bowl(new Score(6));
        Spare spare = new Spare(scores);
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> spare.bowl(new Score(10)));
    }
}