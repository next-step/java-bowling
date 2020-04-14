package bowling.domain.frame;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    private Frame frame;
    private FrameNumber frameNumber;

    @BeforeEach
    void setUp() {
        frameNumber = new FrameNumber(1);
        frame =  new NormalFrame(frameNumber);
    }

    @DisplayName("Frame은 프레임 번호를 가진다.")
    @Test
    void create() {
        Frame expect = new NormalFrame(frameNumber);

        Frame actual = new NormalFrame(frameNumber);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("Frame은 다음 프레임을 가지고 있다.")
    @Test
    void getNormalFrame() {
        Frame expect = new NormalFrame(frameNumber.increase());

        Frame actual = new NormalFrame(frameNumber);

        assertThat(actual.getNext().get()).isEqualTo(expect);
    }

    @DisplayName("Frame은 마지막 프레임을 가지고 있다.")
    @Test
    void getFinalFrame() {
        FrameNumber frameNumber = new FrameNumber(9);
        Frame expect = new FinalFrame(frameNumber.increase());

        Frame actual = new NormalFrame(frameNumber);

        assertThat(actual.getNext().get()).isEqualTo(expect);
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

        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("현재 Frame이 SPARE 일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void spare() {
        Pins first = Pins.of().knockOver(new BowlCount(5));
        Pins second = Pins.of().knockOver(new BowlCount(5));

        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("현재 Frame이 MISS 일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void miss() {
        Pins first = Pins.of().knockOver(new BowlCount(5));
        Pins second = Pins.of().knockOver(new BowlCount(4));

        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isEnd()).isTrue();
    }


    @DisplayName("현재 Frame이 GUTTER 일 경우 다음 프레임으로 넘어갈 수 있다.")
    @Test
    void gutter() {
        Pins first = Pins.of().knockOver(new BowlCount(0));
        Pins second = Pins.of().knockOver(new BowlCount(0));

        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isEnd()).isTrue();
    }
}