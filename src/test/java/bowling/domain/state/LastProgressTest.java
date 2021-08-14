package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.LastProgress;
import bowling.domain.state.LastEnd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LastProgressTest {

    @DisplayName("LastPitching 생성이 가능하다")
    @Test
    void should_make_last_pitching() {
        //arrange, act, assert
        assertThat(LastProgress.of()).isInstanceOf(LastProgress.class);
    }

    @DisplayName("시도 카운트 최대 3번을 다 사용하면 finish true를 반환한다")
    @Test
    public void should_return_true_attempt_count_max() throws Exception {
        //arrange
        LastProgress lastProgress = LastProgress.of();
        lastProgress.hitPins(Pins.of(2));
        lastProgress.hitPins(Pins.of(8));
        lastProgress.hitPins(Pins.of(3));

        //act, assert
        assertTrue(lastProgress.isFinish());
    }

    @DisplayName("BunchState가 finish이면 finish true를 반환한다")
    @Test
    public void should_return_true_when_bunch_is_finish() throws Exception {
        //arrange
        LastProgress lastProgress = LastProgress.of();
        lastProgress.hitPins(Pins.of(1));
        lastProgress.hitPins(Pins.of(5));

        //act, assert
        assertTrue(lastProgress.isFinish());
    }

    @DisplayName("finish가 true이면 Finish State를 반환한다")
    @Test
    public void should_return_finish_state_when_is_finish_true() throws Exception {
        //arrange
        LastProgress lastProgress = new FinishTestingLastProgress();

        //act, assert
        assertThat(lastProgress.hitPins(Pins.of(1))).isInstanceOf(LastEnd.class);
    }

    @DisplayName("BunchState의 hit pin의 empty list를 반환한다")
    @Test
    public void should_return_hit_pins() throws Exception {
        //arrange
        LastProgress lastProgress = LastProgress.of();
        lastProgress.hitPins(Pins.of(1));
        lastProgress.hitPins(Pins.of(5));

        //act, assert
        assertThat(lastProgress.getHitPins()).isEmpty();
    }

    private static class FinishTestingLastProgress extends LastProgress {
        public FinishTestingLastProgress() {
            super();
        }

        @Override
        public boolean isFinish() {
            return true;
        }
    }

}