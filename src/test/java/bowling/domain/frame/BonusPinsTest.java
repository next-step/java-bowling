package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.FrameBowlState;
import bowling.domain.state.FrameBowlStates;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusPinsTest {

    @Test
    void invalid_negative(){
        Pins bonusPins = BonusPins.newInstance();

        assertThatThrownBy(()->bonusPins.down(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_more_than_10(){
        Pins bonusPins = BonusPins.newInstance();

        assertThatThrownBy(()->bonusPins.down(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_state(){
        Pins bonusPins = BonusPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(2);

        assertThatThrownBy(()->bonusPins.down(3))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void invalid_sum_of_down_pin(){
        Pins bonusPins = BonusPins.newInstance();
        bonusPins.down(1);

        assertThatThrownBy(()->bonusPins.down(10))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss(){
        Pins bonusPins = BonusPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(2);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(1, ScoreType.NUMS));
        bowlStates.add(new FrameBowlState(2, ScoreType.NUMS));

        assertThat(bonusPins.getBowlStates()).isEqualTo(new FrameBowlStates(bowlStates));
        //assertThat(finalFrame.()).isEqualTo(frameResult);
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare(){
        Pins bonusPins = BonusPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(9);
        bonusPins.down(9);


        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(1, ScoreType.NUMS));
        bowlStates.add(new FrameBowlState(9, ScoreType.SPARE));
        bowlStates.add(new FrameBowlState(9, ScoreType.NUMS));

        assertThat(bonusPins.getBowlStates()).isEqualTo(new FrameBowlStates(bowlStates));
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike(){
        Pins bonusPins = BonusPins.newInstance();
        bonusPins.down(10);
        bonusPins.down(9);
        bonusPins.down(9);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(10, ScoreType.STRIKE));
        bowlStates.add(new FrameBowlState(9, ScoreType.NUMS));
        bowlStates.add(new FrameBowlState(9, ScoreType.NUMS));

        assertThat(bonusPins.getBowlStates()).isEqualTo(new FrameBowlStates(bowlStates));
    }
}
