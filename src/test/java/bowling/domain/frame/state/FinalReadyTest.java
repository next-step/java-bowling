package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalReadyTest {
    private FinalReady finalReady;

    @BeforeEach
    void setUp() {
        finalReady = new FinalReady();
    }

    @DisplayName("Strike, Strike, Strike")
    @Test
    void bonusAllStrike() {
        BowlCount bowlCount = new BowlCount(10);
        Pins pins = Pins.of().knockOver(bowlCount);

        State first = finalReady.roll(pins);
        State second = finalReady.roll(pins);
        State third = finalReady.roll(pins);

        assertThat(first).isInstanceOf(Strike.class);
        assertThat(second).isInstanceOf(Strike.class);
        assertThat(third).isInstanceOf(Strike.class);
    }

    @DisplayName("Strike, Strike, FirstGutter")
    @Test
    void bonusTwoStrikeFirstBowl() {
        BowlCount bowlCount = new BowlCount(10);
        Pins pins = Pins.of().knockOver(bowlCount);

        State first = finalReady.roll(pins);
        State second = finalReady.roll(pins);
        State third = finalReady.roll(Pins.GUTTER_PINS);

        assertThat(first).isInstanceOf(Strike.class);
        assertThat(second).isInstanceOf(Strike.class);
        assertThat(third).isInstanceOf(FirstGutter.class);
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

        State first = finalReady.roll(firstPins);
        State second = finalReady.roll(secondPins);
        State third = finalReady.roll(thirdPins);

        assertThat(first).isInstanceOf(Strike.class);
        assertThat(second).isInstanceOf(FirstBowl.class);
        assertThat(third).isInstanceOf(Spare.class);
    }

    @DisplayName("Strike, FirstBowl, Gutter")
    @Test
    void bonusOneStrikeFirstBowlGutter() {
        BowlCount firstBowl = new BowlCount(10);
        BowlCount secondBowl = new BowlCount(5);

        Pins firstPins = Pins.of().knockOver(firstBowl);
        Pins secondPins = Pins.of().knockOver(secondBowl);

        State first = finalReady.roll(firstPins);
        State second = finalReady.roll(secondPins);
        State third = finalReady.roll(Pins.GUTTER_PINS);

        assertThat(first).isInstanceOf(Strike.class);
        assertThat(second).isInstanceOf(FirstBowl.class);
        assertThat(third).isInstanceOf(SecondGutter.class);
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

        State first = finalReady.roll(firstPins);
        State second = finalReady.roll(secondPins);
        State third = finalReady.roll(thirdPins);

        assertThat(first).isInstanceOf(FirstBowl.class);
        assertThat(second).isInstanceOf(Spare.class);
        assertThat(third).isInstanceOf(FirstBowl.class);
    }

    @DisplayName("FirstBowl, Gutter")
    @Test
    void normalFirstBowlGutter() {
        BowlCount firstBowl = new BowlCount(5);
        Pins firstPins = Pins.of().knockOver(firstBowl);

        State first = finalReady.roll(firstPins);
        State second = finalReady.roll(Pins.GUTTER_PINS);

        assertThat(first).isInstanceOf(FirstBowl.class);
        assertThat(second).isInstanceOf(SecondGutter.class);
    }

    @DisplayName("FirstBowl, Miss")
    @Test
    void normalFirstBowlMiss() {
        BowlCount firstBowl = new BowlCount(5);
        BowlCount secondBowl = new BowlCount(4);
        Pins firstPins = Pins.of().knockOver(firstBowl);
        Pins secondPins = Pins.of().knockOver(secondBowl);

        State first = finalReady.roll(firstPins);
        State second = finalReady.roll(secondPins);

        assertThat(first).isInstanceOf(FirstBowl.class);
        assertThat(second).isInstanceOf(Miss.class);
    }
}