package bowling.view.enums;

import bowling.domain.frame.Pin;
import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateViewTest {

    private Ready ready;
    private FirstBowl firstBowl;
    private Miss miss;
    private Spare spare;
    private Strike strike;

    @BeforeEach
    void setUp() {
        Pin five = Pin.from(5);
        Pin three = Pin.from(3);
        ready = Ready.getInstance();
        firstBowl = new FirstBowl(five);
        miss = new Miss(five, three);
        spare = new Spare(five, five);
        strike = new Strike();
    }


    @DisplayName("valueOf 각 state에 맞게 StateView를 반환한다.")
    @Test
    void valueOfTest() {
        assertThat(StateView.valueOf(ready)).isEqualTo(StateView.READY_STATE);
        assertThat(StateView.valueOf(firstBowl)).isEqualTo(StateView.FIRST_BOWL_STATE);
        assertThat(StateView.valueOf(miss)).isEqualTo(StateView.MISS_STATE);
        assertThat(StateView.valueOf(spare)).isEqualTo(StateView.SPARE_STATE);
        assertThat(StateView.valueOf(strike)).isEqualTo(StateView.STRIKE_STATE);
    }

    @DisplayName("convert() 메서드 호출 시 출력 조건에 맞게 값을 반환한다.")
    @Test
    void convertTest() {
        assertThat(StateView.READY_STATE.convert(ready)).isEqualTo("");
        assertThat(StateView.FIRST_BOWL_STATE.convert(firstBowl)).isEqualTo("5");
        assertThat(StateView.MISS_STATE.convert(miss)).isEqualTo("5|3");
        assertThat(StateView.SPARE_STATE.convert(spare)).isEqualTo("5|/");
        assertThat(StateView.STRIKE_STATE.convert(strike)).isEqualTo("X");
    }


}
