package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.PinNumbersTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameTest {
    private final Frame doubleStrikeFrame = new Frame(Sequential.DOUBLE_STRIKE);
    private final Frame strikeFrame = new Frame(Sequential.STRIKE);
    private final Frame spareFrame = new Frame(Sequential.SPARE);
    private final Frame noneFrame = new Frame(Sequential.NONE);

    private final Frame strikeFramePreviousSpare = new Frame(Sequential.SPARE, PIN_NUMBERS_STRIKE);
    private final Frame strikeFramePreviousStrike = new Frame(Sequential.STRIKE, PIN_NUMBERS_STRIKE);
    private final Frame strikeFramePreviousDoubleStrike = new Frame(Sequential.DOUBLE_STRIKE, PIN_NUMBERS_STRIKE);
    private final Frame strikeFramePreviousNone = new Frame(Sequential.NONE, PIN_NUMBERS_STRIKE);
    private final Frame spareFirstEightFramePreviousStrike = new Frame(Sequential.STRIKE, PIN_NUMBERS_SPARE_FIRST_EIGHT);
    private final Frame spareFirstFiveFramePreviousStrike = new Frame(Sequential.STRIKE, PIN_NUMBERS_SPARE_FIRST_FIVE);
    private final Frame spareFirstEightFramePreviousSpare = new Frame(Sequential.SPARE, PIN_NUMBERS_SPARE_FIRST_EIGHT);
    private final Frame spareFirstZeroFramePreviousSpare = new Frame(Sequential.SPARE, PIN_NUMBERS_SPARE_FIRST_ZERO);
    private final Frame fivePinsFramePreviousStrike = new Frame(Sequential.STRIKE, PIN_NUMBERS_FIVE);
    private final Frame fivePinsFramePreviousSpare = new Frame(Sequential.SPARE, PIN_NUMBERS_FIVE);
    private final Frame fivePinsFramePreviousNone = new Frame(Sequential.NONE, PIN_NUMBERS_FIVE);
    private final Frame zeroPinsFramePreviousStrike = new Frame(Sequential.STRIKE, PIN_NUMBERS_ZERO);
    private final Frame zeroPinsFramePreviousSpare = new Frame(Sequential.SPARE, PIN_NUMBERS_ZERO);

    @DisplayName("Test frame when Sequential is None")
    @Test
    void testFrameWhenSequentialIsNone() {
        Frame firstFrame = noneFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(strikeFrame);

        Frame secondFrame = noneFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(spareFrame);

        Frame thirdFrame = noneFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(noneFrame);
    }

    private Frame noneFrame() {
        return new Frame(Sequential.NONE);
    }

    @DisplayName("Test frame when Sequential is Spare")
    @Test
    void testFrameWhenSequentialIsSpare() {
        Frame firstFrame = spareFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(strikeFrame);

        Frame secondFrame = spareFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(spareFrame);

        Frame thirdFrame = spareFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(noneFrame);
    }

    private Frame spareFrame() {
        return new Frame(Sequential.SPARE);
    }

    @DisplayName("Test frame when Sequential is Strike")
    @Test
    void testFrameWhenSequentialIsStrike() {
        Frame firstFrame = strikeFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(doubleStrikeFrame);

        Frame secondFrame = strikeFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(spareFrame);

        Frame thirdFrame = strikeFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(noneFrame);
    }

    private Frame strikeFrame() {
        return new Frame(Sequential.STRIKE);
    }

    @DisplayName("Test frame when Sequential is Double Strike")
    @Test
    void testFrameWhenSequentialIsDoubleStrike() {
        Frame firstFrame = doubleStrikeFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(doubleStrikeFrame);

        Frame secondFrame = doubleStrikeFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(spareFrame);

        Frame thirdFrame = doubleStrikeFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(noneFrame);
    }

    private Frame doubleStrikeFrame() {
        return new Frame(Sequential.DOUBLE_STRIKE);
    }

    @DisplayName("Test score when current sequential is Double Strike")
    @Test
    void testDoubleStrike() {
        assertThat(strikeFramePreviousStrike.score(strikeFramePreviousStrike, strikeFramePreviousStrike)).isEqualTo(new Score(30));
        assertThat(strikeFramePreviousStrike.score(strikeFramePreviousStrike, spareFirstEightFramePreviousStrike)).isEqualTo(new Score(28));
        assertThat(strikeFramePreviousStrike.score(strikeFramePreviousStrike, spareFirstFiveFramePreviousStrike)).isEqualTo(new Score(25));
        assertThat(strikeFramePreviousStrike.score(strikeFramePreviousStrike, fivePinsFramePreviousStrike)).isEqualTo(new Score(25));

        assertThat(strikeFramePreviousDoubleStrike.score(strikeFramePreviousStrike, strikeFramePreviousStrike)).isEqualTo(new Score(30));
        assertThat(strikeFramePreviousDoubleStrike.score(strikeFramePreviousStrike, spareFirstEightFramePreviousStrike)).isEqualTo(new Score(28));
        assertThat(strikeFramePreviousDoubleStrike.score(strikeFramePreviousStrike, spareFirstFiveFramePreviousStrike)).isEqualTo(new Score(25));
        assertThat(strikeFramePreviousDoubleStrike.score(strikeFramePreviousStrike, fivePinsFramePreviousStrike)).isEqualTo(new Score(25));
    }

    @DisplayName("Test score when current sequential is Strike")
    @Test
    void testStrike() {
        assertThat(strikeFramePreviousNone.score(spareFirstEightFramePreviousStrike, strikeFramePreviousSpare)).isEqualTo(new Score(20));
        assertThat(strikeFramePreviousNone.score(spareFirstFiveFramePreviousStrike, strikeFramePreviousSpare)).isEqualTo(new Score(20));
        assertThat(strikeFramePreviousNone.score(fivePinsFramePreviousStrike, strikeFramePreviousNone)).isEqualTo(new Score(15));
        assertThat(strikeFramePreviousNone.score(zeroPinsFramePreviousStrike, strikeFramePreviousNone)).isEqualTo(new Score(10));
    }

    @DisplayName("Test score when current sequential is Spare")
    @Test
    void testSpare() {
        assertThat(spareFirstEightFramePreviousStrike.score(strikeFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(20));
        assertThat(spareFirstEightFramePreviousStrike.score(spareFirstEightFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(18));
        assertThat(spareFirstEightFramePreviousStrike.score(spareFirstZeroFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(10));
        assertThat(spareFirstEightFramePreviousStrike.score(fivePinsFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(15));
        assertThat(spareFirstEightFramePreviousStrike.score(zeroPinsFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(10));

        assertThat(spareFirstFiveFramePreviousStrike.score(strikeFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(20));
        assertThat(spareFirstFiveFramePreviousStrike.score(spareFirstEightFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(18));
        assertThat(spareFirstFiveFramePreviousStrike.score(spareFirstZeroFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(10));
        assertThat(spareFirstFiveFramePreviousStrike.score(fivePinsFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(15));
        assertThat(spareFirstFiveFramePreviousStrike.score(zeroPinsFramePreviousSpare, strikeFramePreviousSpare)).isEqualTo(new Score(10));
    }

    @DisplayName("Test score when current sequential is None")
    @Test
    void testNone() {
        assertThat(fivePinsFramePreviousStrike.score(strikeFramePreviousNone, strikeFramePreviousStrike)).isEqualTo(new Score(5));
        assertThat(fivePinsFramePreviousStrike.score(spareFirstEightFramePreviousStrike, strikeFramePreviousStrike)).isEqualTo(new Score(5));
        assertThat(fivePinsFramePreviousStrike.score(fivePinsFramePreviousNone, spareFirstEightFramePreviousStrike)).isEqualTo(new Score(5));

        assertThat(fivePinsFramePreviousSpare.score(strikeFramePreviousNone, strikeFramePreviousStrike)).isEqualTo(new Score(5));
        assertThat(fivePinsFramePreviousSpare.score(spareFirstEightFramePreviousStrike, strikeFramePreviousStrike)).isEqualTo(new Score(5));
        assertThat(fivePinsFramePreviousSpare.score(fivePinsFramePreviousNone, spareFirstEightFramePreviousStrike)).isEqualTo(new Score(5));

        assertThat(fivePinsFramePreviousNone.score(strikeFramePreviousNone, strikeFramePreviousStrike)).isEqualTo(new Score(5));
        assertThat(fivePinsFramePreviousNone.score(spareFirstEightFramePreviousStrike, strikeFramePreviousStrike)).isEqualTo(new Score(5));
        assertThat(fivePinsFramePreviousNone.score(fivePinsFramePreviousNone, spareFirstEightFramePreviousStrike)).isEqualTo(new Score(5));
    }
}
