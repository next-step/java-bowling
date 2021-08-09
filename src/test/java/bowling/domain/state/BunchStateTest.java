package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.state.result.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BunchStateTest {

    @DisplayName("BunchState 생성이 가능하다")
    @Test
    void should_make_bunch_state() {
        //arrange, act, assert
        assertThat(BunchState.of()).isInstanceOf(BunchState.class);
    }

    @DisplayName("BunchState는 isMiss을 false로 반환해야한다")
    @Test
    void should_return_false_is_miss() {
        //arrange
        BunchState bunchState = BunchState.of();

        //act, assert
        assertFalse(bunchState.isMiss());
    }

    @DisplayName("BunchState는 isAllHit을 false로 반환해야한다")
    @Test
    void should_return_false_is_all_hit() {
        //arrange
        BunchState bunchState = BunchState.of();

        //act, assert
        assertFalse(bunchState.isAllHit());
    }

    @DisplayName("hitPins에 따라 마지막 state가 바뀐다")
    @Test
    void should_change_state_hit_pins() {
        //arrange
        Pins pins = Pins.of(10);
        BunchState bunchState = BunchState.of();

        //act
        bunchState.hitPins(pins);

        //assert
        List<CommonState> states = bunchState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(Strike.class);
    }

    @DisplayName("BunchState의 hitPins는 empty를 반환해야한다")
    @Test
    void should_return_empty_list() {
        //arrange
        BunchState bunchState = BunchState.of();

        //act, assert
        assertThat(bunchState.getHitPins()).isEmpty();
    }

    @DisplayName("BunchState의 state list를 반환해야한다")
    @Test
    void should_return_state_list() {
        //arrange
        BunchState bunchState = BunchState.of();

        //act, assert
        List<CommonState> states = bunchState.getState();
        assertThat(states.get(0)).isInstanceOf(FirstPitching.class);
    }

    @DisplayName("BunchState는 all hit될 경우 추가 기회가 생긴다")
    @Test
    void should_add_extra_chance() {
        //arrange
        BunchState bunchState = new ExtraChangeTestingBunchState();

        //act
        bunchState.addExtraChance();

        //assert
        List<CommonState> states = bunchState.getState();
        assertAll(
                () -> assertThat(states.get(states.size() - 1)).isInstanceOf(FirstPitching.class),
                () -> assertThat(states.size()).isEqualTo(2)
        );
    }

    private static class ExtraChangeTestingBunchState extends BunchState {
        public ExtraChangeTestingBunchState() {
            super();
        }

        @Override
        protected boolean isNotAllHit() {
            return false;
        }
    }

}