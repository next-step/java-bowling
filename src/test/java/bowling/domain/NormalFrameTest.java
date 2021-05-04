package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.state.Miss;
import bowling.domain.frame.state.Spare;
import bowling.domain.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {
    @Test
    @DisplayName("노멀 프레임에는 2번 투구만 가능하다.")
    void bowlTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(5);
        normalFrame.bowl(5);

        assertThatThrownBy(() -> normalFrame.bowl(3))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("노멀 프레임 MISS 테스트")
    void missTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(5);
        normalFrame.bowl(3);

        assertThat(normalFrame.getLatestState()).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("노멀 프레임 STRIKE 테스트")
    void strikeTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(10);

        assertThat(normalFrame.getLatestState()).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("노멀 프레임 SPARE 테스트")
    void spareTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(7);
        normalFrame.bowl(3);

        assertThat(normalFrame.getLatestState()).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("노멀 프레임 STRIKE면 1번만 투구 가능.")
    void onlyOneBowlIfStrikeTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(10);

        assertThatThrownBy(() -> normalFrame.bowl(3))
                .isInstanceOf(IllegalStateException.class);
    }
}
