package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    private FinalFrame frame;

    @BeforeEach
    void setUp() {
        frame = (FinalFrame) FinalFrame.of();
    }

    @DisplayName("게임 세번 진행 시 게임종료")
    @Test
    void bowl_threeTime_thanGameOver() {
        // given
        Pins downPins = Pins.DOWN_ALL;

        // when
        frame.bowl(downPins);
        frame.bowl(downPins);
        frame.bowl(downPins);

        assertThat(frame.isGameOver()).isTrue();
    }

    @DisplayName("게임 한번 진행 시 계속 진행한다")
    @Test
    void bowl_oneTime_thanContinueGame() {
        // given
        Pins first = Pins.valueOf(5);

        // when
        frame.bowl(first);

        assertThat(frame.isGameOver()).isFalse();
    }

    @DisplayName("게임 두번 결과가 ")
    @Test
    void bowl_twoTime_thanContinueGame() {
        // given
        Pins first = Pins.valueOf(5);

        // when
        frame.bowl(first);

        assertThat(frame.isGameOver()).isFalse();
    }

    @Test
    void downPinTwo_nextFrame() {
        // given
        Pins first = Pins.valueOf(5);
        Pins second = Pins.valueOf(5);

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isFalse();
    }

    @Test
    void downPin10And0_nextFrame() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(0);

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isFalse();
    }

    @Test
    void downPin0And10_nextFrame() {
        // given
        Pins first = Pins.valueOf(0);
        Pins second = Pins.valueOf(10);

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isFalse();
    }
}
