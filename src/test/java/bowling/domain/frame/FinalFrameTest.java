package bowling.domain.frame;

import bowling.domain.frame.state.*;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private FrameNumber frameNumber;
    private Frame finalFrame;

    @BeforeEach
    void setUp() {
        frameNumber = new FrameNumber(10);
        finalFrame = new FinalFrame(frameNumber);
    }

    @DisplayName("마지막 프레임은 다음 프레임이 존재하지 않는다.")
    @Test
    void getNextFrame() {
        Frame actual = new FinalFrame(frameNumber);

        assertThat(actual.getNext()).isEmpty();
    }

    @DisplayName("Strike, Strike, Strike")
    @Test
    void bonusAllStrike() {
        BowlCount bowlCount = new BowlCount(10);
        Pins pins = Pins.of().knockOver(bowlCount);
        finalFrame.bowl(pins);
        finalFrame.bowl(pins);
        finalFrame.bowl(pins);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(Strike.class);
        assertThat(states.get(1)).isInstanceOf(Strike.class);
        assertThat(states.get(2)).isInstanceOf(Strike.class);
    }

    @DisplayName("Strike, Strike, FirstGutter")
    @Test
    void bonusTwoStrikeFirstBowl() {
        BowlCount bowlCount = new BowlCount(10);
        Pins pins = Pins.of().knockOver(bowlCount);
        finalFrame.bowl(pins);
        finalFrame.bowl(pins);
        finalFrame.bowl(Pins.GUTTER_PINS);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(Strike.class);
        assertThat(states.get(1)).isInstanceOf(Strike.class);
        assertThat(states.get(2)).isInstanceOf(FirstGutter.class);
    }

    @DisplayName("Strike, FirstBowl, Spare")
    @Test
    void bonusOneStrikeFirstBowlSpare() {
        BowlCount firstBowl = new BowlCount(10);
        BowlCount secondBowl = new BowlCount(5);
        BowlCount thirdBowl = new BowlCount(5);

        Pins firstPins = Pins.of().knockOver(firstBowl);
        Pins secondPins = Pins.of().knockOver(secondBowl);
        Pins thirdPins = Pins.of().knockOver(thirdBowl);

        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(Strike.class);
        assertThat(states.get(1)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(2)).isInstanceOf(Spare.class);
    }

    @DisplayName("Strike, FirstBowl, Gutter")
    @Test
    void bonusOneStrikeFirstBowlGutter() {
        BowlCount firstBowl = new BowlCount(10);
        BowlCount secondBowl = new BowlCount(5);

        Pins firstPins = Pins.of().knockOver(firstBowl);
        Pins secondPins = Pins.of().knockOver(secondBowl);

        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(Pins.GUTTER_PINS);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(Strike.class);
        assertThat(states.get(1)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(2)).isInstanceOf(SecondGutter.class);
    }

    @DisplayName("FirstBowl, Spare, FirstBowl")
    @Test
    void bonusFirstBowlSpareFirstBowl() {
        BowlCount firstBowl = new BowlCount(5);
        BowlCount secondBowl = new BowlCount(5);
        BowlCount thirdBowl = new BowlCount(4);

        Pins firstPins = Pins.of().knockOver(firstBowl);
        Pins secondPins = Pins.of().knockOver(secondBowl);
        Pins thirdPins = Pins.of().knockOver(thirdBowl);

        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(1)).isInstanceOf(Spare.class);
        assertThat(states.get(2)).isInstanceOf(FirstBowl.class);
    }

    @DisplayName("FirstBowl, Gutter")
    @Test
    void normalFirstBowlGutter() {
        BowlCount firstBowl = new BowlCount(5);
        Pins firstPins = Pins.of().knockOver(firstBowl);

        finalFrame.bowl(firstPins);
        finalFrame.bowl(Pins.GUTTER_PINS);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(1)).isInstanceOf(SecondGutter.class);
    }

    @DisplayName("FirstBowl, Miss")
    @Test
    void normalFirstBowlMiss() {
        BowlCount firstBowl = new BowlCount(5);
        BowlCount secondBowl = new BowlCount(4);

        Pins firstPins = Pins.of().knockOver(firstBowl);
        Pins secondPins = Pins.of().knockOver(secondBowl);

        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);

        final List<State> states = finalFrame.getStates().getList();

        assertThat(states.get(0)).isInstanceOf(FirstBowl.class);
        assertThat(states.get(1)).isInstanceOf(Miss.class);
    }
}