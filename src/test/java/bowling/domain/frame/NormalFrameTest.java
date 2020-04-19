package bowling.domain.frame;

import bowling.domain.frame.state.*;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    private Frame frame;
    private FrameNumber frameNumber;

    @BeforeEach
    void setUp() {
        frameNumber = FrameNumber.ofFirst();
        frame =  new NormalFrame(frameNumber, null);
    }

    @DisplayName("현재 Frame이 진행중 상태일 경우 다음 프레임으로 넘어갈 수 없다.")
    @Test
    void running() {
        Pins pins = Pins.of().knockOver(new BowlCount(5));

        frame.bowl(pins);

        assertThat(frame.isEnd()).isFalse();
    }

    @DisplayName("현재 Frame이 Strike일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void strike() {
        Pins pins = Pins.of().knockOver(new BowlCount(10));

        frame.bowl(pins);
        final List<State> states = frame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(Strike.class);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("현재 Frame이 SPARE 일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void spare() {
        Pins first = Pins.of().knockOver(new BowlCount(5));
        Pins second = Pins.of().knockOver(new BowlCount(5));

        frame.bowl(first);
        frame.bowl(second);

        final List<State> states = frame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(1)).isInstanceOf(Spare.class);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("현재 Frame이 MISS 일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void miss() {
        Pins first = Pins.of().knockOver(new BowlCount(5));
        Pins second = Pins.of().knockOver(new BowlCount(4));

        frame.bowl(first);
        frame.bowl(second);
        final List<State> states = frame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(1)).isInstanceOf(Miss.class);
        assertThat(frame.isEnd()).isTrue();
    }


    @DisplayName("현재 Frame이 GUTTER 일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void gutter() {
        Pins first = Pins.of().knockOver(new BowlCount(0));
        Pins second = Pins.of().knockOver(new BowlCount(0));

        frame.bowl(first);
        frame.bowl(second);

        final List<State> states = frame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(FirstGutter.class);
        assertThat(states.get(1)).isInstanceOf(SecondGutter.class);
        assertThat(frame.isEnd()).isTrue();
    }
}