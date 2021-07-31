package bowling.domain.frame;

import bowling.domain.state.Preparation;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 하나에 대한 테스트")
class FrameTest {

    @DisplayName("프레임은 하나의 상태값을 가진다")
    @Test
    void init() {
        SomeFrame someFrame = new SomeFrame(Preparation.instance());

        assertThat(someFrame).isInstanceOf(Frame.class);
    }


    class SomeFrame extends Frame {
        protected SomeFrame(State state) {
            super(state);
        }
    }
}
