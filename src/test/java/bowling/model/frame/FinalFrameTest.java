package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.state.finished.Miss;
import bowling.model.state.finished.Spare;
import bowling.model.state.finished.Strike;
import bowling.model.state.Open;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FinalFrameTest {
    private static final List<Pins> PINS = IntStream.rangeClosed(0, 10)
            .boxed()
            .map(Pins::from)
            .collect(Collectors.toList());

    @Test
    void bowling_OPEN_MISS(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(1));
        finalFrame.bowling(PINS.get(8));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Miss.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(9);
    }

    @Test
    void bowling_OPEN_SPARE_OPEN(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(1));
        finalFrame.bowling(PINS.get(9));
        finalFrame.bowling(PINS.get(9));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Open.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(19);
    }

    @Test
    void bowling_OPEN_SPARE_STRIKE(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(1));
        finalFrame.bowling(PINS.get(9));
        finalFrame.bowling(PINS.get(10));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Strike.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(20);
    }

    @Test
    void bowling_STRIKE_OPEN_SPARE(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(10));
        finalFrame.bowling(PINS.get(9));
        finalFrame.bowling(PINS.get(1));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Spare.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(20);
    }

    @Test
    void bowling_STRIKE_OPEN_MISS(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(10));
        finalFrame.bowling(PINS.get(8));
        finalFrame.bowling(PINS.get(1));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Miss.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(19);
    }

    @Test
    void bowling_STRIKE_STRIKE_OPEN(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(10));
        finalFrame.bowling(PINS.get(10));
        finalFrame.bowling(PINS.get(9));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Open.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(29);
    }

    @Test
    void bowling_STRIKE_STRIKE_STRIKE(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(10));
        finalFrame.bowling(PINS.get(10));
        finalFrame.bowling(PINS.get(10));

        assertThat(finalFrame.isFinished()).isTrue();
        assertThat(finalFrame.states.last()).isInstanceOf(Strike.class);
        assertThat(finalFrame.getScore().orElse(0)).isEqualTo(30);
    }

    @Test
    void bowling_OPEN_MISS_STRIKE() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(8));
        finalFrame.bowling(PINS.get(1));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> finalFrame.bowling(PINS.get(10)))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }

    @Test
    void bowling_OPEN_MISS_OPEN() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowling(PINS.get(8));
        finalFrame.bowling(PINS.get(1));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> finalFrame.bowling(PINS.get(1)))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }
}