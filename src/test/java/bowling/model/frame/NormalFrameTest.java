package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.First;
import bowling.model.state.Miss;
import bowling.model.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임이 생성될 때 프레임 번호는 1, 상태는 레디이다.")
    void firstFrame() {
        NormalFrame normalFrame = NormalFrame.first();

        assertThat(normalFrame.getNumber()).isEqualTo(1);
        assertThat(normalFrame.getState()).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("게임이 진행되면 상태가 바뀐다.")
    void bowl() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(Pin.of(5));

        assertThat(normalFrame.getState()).isInstanceOf(First.class);
    }
}
