package bowling.model.state;

import bowling.model.state.finishedState.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StatesTest {

    @Test
    void bowling() {
        States states = new States();
        states.bowling(10);
        assertThat(states.last()).isInstanceOf(Strike.class);
    }

    @Test
    void isFinished() {
        States states = new States();
        assertThat(states.isFinished()).isFalse();

        states.bowling(10);
        assertThat(states.isFinished()).isTrue();
    }

    @Test
    void isMaxScore() {
        States states = new States();
        assertThat(states.isMaxScore()).isFalse();

        states.bowling(10);
        assertThat(states.isFinished()).isTrue();
    }

    @Test
    void changeLastToBonusFrame_스트라이크_후_보너스로_전환() {
        States states = new States();
        states.bowling(10);
        states.changeLastToBonusFrame();
        assertThat(states.last()).isInstanceOf(BonusOpen.class);
    }

    @Test
    void changeLastToBonusFrame_스페어_후_보너스로_전환() {
        States states = new States();
        states.bowling(9);
        states.bowling(1);
        states.changeLastToBonusFrame();
        assertThat(states.last()).isInstanceOf(BonusOpen.class);
    }

    @Test
    void changeLastToBonusFrame_비정상() {
        States states = new States();
        states.bowling(9);
        assertThatIllegalArgumentException()
                .isThrownBy(states::changeLastToBonusFrame)
                .withMessage("Strike 혹은 Spare만 보너스 프레임이 가능합니다.");
    }

    @Test
    void isStart() {
        States states = new States();
        assertThat(states.last()).isInstanceOf(Start.class);
    }
}